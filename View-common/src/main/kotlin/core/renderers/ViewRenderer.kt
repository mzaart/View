package core.renderers

import core.views.View

interface ViewRenderer {

    fun invalidate(viewId: Int)

    fun setOnClickListener(listener: (View) -> Unit)
    fun setOnLongClickListener(listener: (View) -> Unit)
    fun setOnResizeListener(listener: (View) -> Unit)
}