package core.loaders.builders.dsl

import core.loaders.viewTree.treeLoaders.ViewTreeLoader
import core.views.layouts.Layout

@Suppress("ClassName")
object viewTree {

    infix fun withRoot(root: Layout): Layout {
        val viewTree = DslViewTreeLoader()
        viewTree.rootLayout = root
        return viewTree.rootLayout
    }
}

class DslViewTreeLoader: ViewTreeLoader {

    lateinit var rootLayout: Layout
    override fun loadViewTree() = rootLayout
}