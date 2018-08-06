package core.loaders.builders

import core.views.View
import core.views.layouts.Layout
import utils.extensions.notNull

abstract class ViewBuilder<V: View>(
        protected val parent: Layout,
        protected val attributes: Map<String, String>
) {

    fun buildView(): V {
        val view = getViewInstance()

        notNull(attributes["id"]) {id -> view.id = getIdFromString(id)}
        notNull(attributes["width"]) {w -> w.toDouble()}
        notNull(attributes["height"]) {h -> h.toDouble()}

        applyAttributes(view)

        return view
    }

    protected fun getIdFromString(id: String) = id.hashCode()

    protected abstract fun getViewInstance(): V
    protected abstract fun applyAttributes(v: V)
}