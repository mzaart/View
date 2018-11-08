package core.loaders.builders.layouts

import core.loaders.builders.AbstractViewBuilder
import core.views.View
import core.views.layouts.Layout
import utils.extensions.nonNull
import utils.mapBased.keys.delegates.nullable.BoolRWKey

abstract class LayoutBuilder<L: Layout>: AbstractViewBuilder<L>() {

    var scrollX by BoolRWKey
    var scrollY by BoolRWKey

    protected val children: MutableList<Pair<View, Map<String, Any?>>> = mutableListOf()

    fun addChild(child: View, childKeys: Map<String, Any?>): LayoutBuilder<L> {
        children += child to childKeys
        return this
    }

    override fun beforeProduction() {
        scrollX.nonNull { view.scrollX = it }
        scrollY.nonNull { view.scrollY = it }
        addChildrenToViews()
    }

    protected open fun addChildrenToViews() {
        children.forEach { view.addChild(it.first) }
    }
}