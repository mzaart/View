import core.loaders.InvalidViewTreeException
import core.loaders.builders.ViewBuilder
import core.loaders.builders.layouts.LayoutBuilder
import core.loaders.builders.layouts.LinearLayoutBuilder
import core.loaders.viewTree.LayoutNode
import core.loaders.viewTree.Node
import core.providers.viewBuilderProviders.ViewBuilderProvider
import core.views.layouts.Layout
import di.Inject
import utils.toID

abstract class ViewTreeLoader() {

    private val requiredKeys = setOf(ViewBuilder.Keys.TYPE)
    private val ids = mutableSetOf<Int>()
    private val builderProvider by Inject(ViewBuilderProvider::class)
    private var viewCount = 0

    init {
        ids.add(viewCount)
    }

    fun loadViewTree(): Layout {
        val bldr = LinearLayoutBuilder()
        bldr.applyAttributes(mapOf(Pair("direction", "vertical")))
        visitNode(getRootNode(), bldr as LayoutBuilder<Layout>)
        return bldr.build()
    }

    abstract fun getRootNode(): Node

    private fun visitNode(node: Node, parentBldr: LayoutBuilder<Layout>) {
        val content = node.content
        val keys = content.keys
        if (keys.intersect(requiredKeys) != requiredKeys) {
            throw InvalidViewTreeException((requiredKeys - content.keys) as Set<String>)
        }

        val typeKey = ViewBuilder.Keys.TYPE.getName()
        val builder = builderProvider.getBuilderInstance(content[typeKey]!!)
        builder.applyAttributes(content)

        val view = builder.build()

        view.id = content[ViewBuilder.Keys.ID.getName()]?.toID() ?: viewCount++
        if (ids.contains(view.id)) {
            throw IllegalArgumentException("Duplicate ID ${view.id}")
        }

        if ((view is Layout).xor(node is LayoutNode)) {
            throw InvalidViewTreeException("The ViewTree node and constructed View do not match")
        }

        if (node is LayoutNode) {
            node.children.forEach { n -> visitNode(n, builder as LayoutBuilder<Layout>) }
        }
        parentBldr.addChild(view, content)
    }
}