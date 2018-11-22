package core.renderers

import core.views.View
import core.views.layouts.Layout

class PlaceholderTreeRenderer: ViewTreeRenderer {

    override fun setRoot(layout: Layout) {}

    override fun invalidate(view: View) {}
}