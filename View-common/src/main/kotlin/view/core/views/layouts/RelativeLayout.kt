package view.core.views.layouts

import view.core.views.View

/**
 * A layout where child views are positioned relative to each other and to the layout itself.
 *
 * The way child views are positioned is determined by a set of rules defined in [RelativeLayout.Positioning].
 * Note that to achieve a correct behaviour, there should be no conflicting rules or circular dependencies between
 * the position of the child views or between the size of the layout and the position of the child views.
 *
 */
open class RelativeLayout: Layout() {

    /**
     * Contains rules that determine a child view's position inside a [RelativeLayout].
     *
     * Rules are divided into two types, rules that determine the child's horizontal and vertical position.
     * Note that you are not allowed to have multiple rules that belong to the same category.
     */
    enum class Positioning {

        /**
         * Aligns the child view to its parent's top edge.
         */
        ALIGN_PARENT_TOP,

        /**
         * Aligns the child view to its parent's bottom edge.
         */
        ALIGN_PARENT_BOTTOM,

        /**
         * Aligns the child view to its parent's start.
         */
        ALIGN_PARENT_START,

        /**
         * Aligns the child view to it's parent end.
         */
        ALIGN_PARENT_END,

        /**
         * Centers the child vertically in its parent.
         */
        CENTER_VERTICAL,

        /**
         * Centers the child horizontally in its parent
         */
        CENTER_HORIZONTAL,

        /**
         * Aligns the bottom edge of the view to the top edge of another child view.
         */
        TOP_OF,

        /**
         * Aligns the top edge of the view to the bottom edge of another child view.
         */
        BOTTOM_OF,

        /**
         * Aligns the end edge of the view to the start edge of another child view.
         */
        START_OF,

        /**
         * Aligns the start edge of the view to the end edge of another child view.
         */
        END_OF,

        /**
         * Aligns the top edge of the view to the top edge of another child view.
         */
        ALIGN_TOP,

        /**
         * Aligns the bottom edge of the view to the bottom edge of another child view.
         */
        ALIGN_BOTTOM,

        /**
         * Aligns the start edge of the view to the start edge of another child view.
         */
        ALIGN_START,

        /**
         * Aligns the end edge of the view to the end edge of another child view.
         */
        ALIGN_END
    }

    /**
     * Contains the position rules of child views.
     *
     * The list of rules at index i correspond to the rules of the i-th child view.
     */
    val positions: MutableList<List<Pair<Positioning, Int>>> = mutableListOf()

    /**
     * Contains sets of conflicting rules.
     *
     * Generally, rules of the same category, horizontal or vertical, conflict with each other.
     */
    val conflictingPositionings = listOf(
            setOf(
                    Positioning.ALIGN_PARENT_TOP, Positioning.ALIGN_PARENT_BOTTOM,
                    Positioning.TOP_OF, Positioning.BOTTOM_OF, Positioning.CENTER_VERTICAL,
                    Positioning.ALIGN_TOP, Positioning.ALIGN_BOTTOM
            ),
            setOf(
                    Positioning.ALIGN_PARENT_START, Positioning.ALIGN_PARENT_END,
                    Positioning.START_OF, Positioning.END_OF, Positioning.CENTER_HORIZONTAL,
                    Positioning.ALIGN_START, Positioning.ALIGN_END
            )
    )

    /**
     * Adds a view to the layout.
     *
     * @param child The view to add.
     * @param positionings List of pairs containing the position rule and the ID of the view is positioned relative
     * to.
     * @return True if the child was added, false otherwise
     *
     * @throws IllegalArgumentException If the positioning list contains conflicting rules.
     */
    fun addChild(child: View, positionings: List<Pair<Positioning, Int>>): Boolean {
        // check for invalid positioning
        var verticalSet = false
        var horizontalSet = false
        positionings.forEach { p ->
            if (p.first in conflictingPositionings[0]) {
                if (verticalSet) {
                    throw IllegalArgumentException("Invalid Positionings")
                }
                verticalSet = true
            } else {
                if (horizontalSet) {
                    throw IllegalArgumentException("Invalid Positionings")
                }
                horizontalSet = true
            }
        }

        positions += positionings
        return childViews.add(child)
    }

    /**
     * Adds a view to the layout.
     *
     * Views added using this method would have the [Positioning.ALIGN_PARENT_TOP] rule by default.
     *
     * @param child The view to be added
     * @return True if the child was added, false otherwise.
     */
    override fun addChild(child: View) = addChild(child, listOf(Positioning.ALIGN_PARENT_TOP to id))

    /**
     * Removes a child view.
     *
     * @param child The view to be removed.
     * @return True if the child was removed, false otherwise.
     */
    override fun removeChild(child: View): Boolean {
        val index = childViews.indexOf(child)
        if (index != -1) {
            positions.removeAt(index)
            return true
        }
        return false
    }
}