package core.views.layouts

import core.views.View
import core.views.viewDelegates.NullableViewProperty

class LinearLayout: Layout() {

    var direction: Direction? by NullableViewProperty()

    enum class Direction {
        VERTICAL,
        HORIZONTAL
    }

    fun addChild(child: View) = children.add(child)
}
