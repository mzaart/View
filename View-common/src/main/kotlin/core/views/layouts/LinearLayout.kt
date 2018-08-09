package core.views.layouts

import core.views.propertyDelegates.NullableViewProperty

open class LinearLayout: Layout() {

    var direction: Direction? by NullableViewProperty()

    enum class Direction {
        VERTICAL,
        HORIZONTAL
    }
}
