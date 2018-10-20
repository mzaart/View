package core.views.layouts

import core.renderers.ViewTreeRenderer
import core.views.View
import di.inject
import utils.observables.ObservableCollection

abstract class Layout: View() {

    private val renderer by inject<ViewTreeRenderer>()

    protected val childViews: MutableCollection<View> = ObservableCollection { renderer.invalidate(id) }

    fun children() = childViews.toList()

    open fun addChild(child: View): Boolean {
        return childViews.add(child)
    }

    open fun removeChild(child: View) = childViews.remove(child)
}