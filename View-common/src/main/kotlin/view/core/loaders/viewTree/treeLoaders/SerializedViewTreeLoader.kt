package view.core.loaders.viewTree.treeLoaders

import view.core.loaders.builders.ViewBuilder
import view.core.loaders.builders.layouts.LayoutBuilder
import view.core.loaders.builders.layouts.LinearLayoutBuilder
import view.core.loaders.viewTree.IllegalViewTreeException
import view.core.loaders.viewTree.nodes.LayoutNode
import view.core.loaders.viewTree.nodes.Node
import view.core.views.layouts.Layout
import view.di.inject

/**
 * Base class for tree loaders that load view trees from serialized formats.
 *
 * @constructor Initializes the object with a root node
 * @param rootNode The root of the view tree
 */
open class SerializedViewTreeLoader(protected val rootNode: LayoutNode): ViewTreeLoader {

    /**
     * Loads the view tree
     *
     * @return The root of the loaded view tree
     */
    override fun loadViewTree(): Layout {
        val bldr = LinearLayoutBuilder().applyKeys(mapOf(
                "id" to "ViewTreeRoot",
                "direction" to "VERTICAL"
        ))
        visitNode(rootNode, bldr as LayoutBuilder<Layout>)
        return bldr.build()
    }

    private fun visitNode(node: Node, parentBldr: LayoutBuilder<Layout>) {
        val content = node.content

        val type = content["type"]
                ?: throw IllegalViewTreeException(setOf("type"))
        if (type !is String) {
            throw IllegalViewTreeException("NullableRWKey 'type' should be a String")
        }
        val builder by inject<ViewBuilder<*>>(type)

        if ((builder is LayoutBuilder<*>).xor(node is LayoutNode)) {
            throw IllegalViewTreeException("The ViewTree node and constructed View do not match")
        }

        if (node is LayoutNode) {
            node.children.forEach { n -> visitNode(n, builder as LayoutBuilder<Layout>) }
        }

        val view = builder.applyKeys(content).build()
        parentBldr.addChild(view, content)
    }
}