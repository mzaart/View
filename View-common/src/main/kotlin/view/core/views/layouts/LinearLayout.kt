package view.core.views.layouts

import view.core.views.propertyDelegates.NullableViewProperty

/**
 * This class represents a layout that arranges its elements in a linear manner, either horizontally or vertically.
 */
open class LinearLayout: Layout() {

    /**
     * Specifies the direction, horizontal or vertical, that the child views will be arranged in.
     */
    var direction: Direction? by NullableViewProperty()

    /**
     * Represents a direction, horizontal or vertical, that the child views will be arranged in.
     */
    enum class Direction {

        /**
         * Arrange child views vertically.
         */
        VERTICAL,

        /**
         * Arrange child views horizontally.
         */
        HORIZONTAL
    }
}
