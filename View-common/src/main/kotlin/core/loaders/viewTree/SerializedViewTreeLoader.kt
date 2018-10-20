package core.loaders.viewTree

import core.loaders.builders.ViewBuilder
import core.loaders.builders.layouts.LayoutBuilder
import core.loaders.builders.layouts.LinearLayoutBuilder
import core.loaders.viewTree.nodes.LayoutNode
import core.loaders.viewTree.nodes.Node
import core.views.layouts.Layout
import di.inject

abstract class SerializedViewTreeLoader: ViewTreeLoader {

    override fun loadViewTree(): Layout {
        val bldr = LinearLayoutBuilder().applyKeys(mapOf(
                "id" to "ViewTreeRoot",
                "direction" to "VERTICAL"
        ))
        visitNode(getRootNode(), bldr as LayoutBuilder<Layout>)
        return bldr.build()
    }

    abstract fun getRootNode(): Node

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