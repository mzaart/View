package core.loaders.builders.layouts

import core.loaders.builders.ViewBuilder
import core.views.View
import core.views.layouts.Layout

abstract class LayoutBuilder<L: Layout>: ViewBuilder<L>() {

    protected val children: MutableList<Pair<View, Map<String, String>>> = mutableListOf()

    fun addChild(child: View, childKeys: Map<String, String>): LayoutBuilder<L> {
        children += child to childKeys
        return this
    }

    open fun addChildren() {
        children.forEach { view.addChild(it.first) }
    }

    override fun build(): L {
        val layout = super.build()
        addChildren()
        return layout
    }
}