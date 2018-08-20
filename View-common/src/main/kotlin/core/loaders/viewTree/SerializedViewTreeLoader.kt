package core.loaders.viewTree

import core.loaders.builders.layouts.LayoutBuilder
import core.loaders.builders.layouts.LinearLayoutBuilder
import core.loaders.viewTree.nodes.LayoutNode
import core.loaders.viewTree.nodes.Node
import core.providers.viewBuilderProviders.ViewBuilderProvider
import core.views.layouts.Layout
import di.Inject

abstract class SerializedViewTreeLoader: ViewTreeLoader {

    private val builderProvider by Inject(ViewBuilderProvider::class)

    override fun loadViewTree(): Layout {
        val bldr = LinearLayoutBuilder().applyKeys(mapOf(Pair("direction", "vertical")))
        visitNode(getRootNode(), bldr as LayoutBuilder<Layout>)
        return bldr.build()
    }

    abstract fun getRootNode(): Node

    private fun visitNode(node: Node, parentBldr: LayoutBuilder<Layout>) {
        val content = node.content

        val typeKey = content["type"] ?: throw IllegalViewTreeException(setOf("type"))
        val builder = builderProvider.getBuilderInstance(content[typeKey]!!)
                .applyKeys(content)

        if ((builder is LayoutBuilder<*>).xor(node is LayoutNode)) {
            throw IllegalViewTreeException("The ViewTree node and constructed DslView do not match")
        }

        if (node is LayoutNode) {
            node.children.forEach { n -> visitNode(n, builder as LayoutBuilder<Layout>) }
        }

        val view = builder.build()
        parentBldr.addChild(view, content)
    }
}