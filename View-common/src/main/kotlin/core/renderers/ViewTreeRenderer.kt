package core.renderers

import core.views.View
import core.views.layouts.Layout

interface ViewTreeRenderer {

    fun setRoot(layout: Layout)
    fun invalidate(view: View)
}