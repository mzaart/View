package core.loaders.builders.layouts

import core.loaders.builders.ViewBuilder
import core.views.View
import core.views.layouts.Layout

abstract class LayoutBuilder<L: Layout>: ViewBuilder<L>() {

    protected val children: MutableList<Pair<View, Map<String, Any?>>> = mutableListOf()

    fun addChild(child: View, childKeys: Map<String, Any?>): LayoutBuilder<L> {
        children += child to childKeys
        return this
    }

    override fun beforeProduction() {
        children.forEach { view.addChild(it.first) }
    }
}