package core.views.layouts

import core.renderers.ViewTreeRenderer
import core.views.View
import di.inject
import utils.extensions.toID
import utils.observables.ObservableCollection

abstract class Layout: View() {

    private val renderer by inject<ViewTreeRenderer>()

    protected val childViews: MutableCollection<View> = ObservableCollection { renderer.invalidate(this) }

    fun children() = childViews.toList()

    fun find(id: String) = find(id.toID())

    fun find(id: Int): View {
        val queue = children().toMutableList()
        while (!queue.isEmpty()) {
            val view = queue.removeAt(0)
            if (view.id == id) {
                return view
            }
            if (view is Layout) {
                queue += view.children()
            }
        }
        throw NoSuchElementException("No View exists for the given ID")
    }

    open fun addChild(child: View): Boolean {
        val added = childViews.add(child)
        if (added) {
            child.parent = this
            renderer.invalidate(this)
        }
        return added
    }

    open fun removeChild(child: View): Boolean {
        val removed = childViews.remove(child)
        if (removed) {
            child.parent = null
            renderer.invalidate(this)
        }
        return removed;
    }
}