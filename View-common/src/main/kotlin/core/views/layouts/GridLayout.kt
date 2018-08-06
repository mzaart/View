package core.views.layouts

import core.views.View

class GridLayout: Layout() {

    fun addChild(child: View) = children.add(child)
}