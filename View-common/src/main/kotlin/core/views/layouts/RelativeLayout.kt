package core.views.layouts

import core.views.View

class RelativeLayout: Layout() {

    enum class Positioning {
        ALIGN_PARENT_TOP,
        ALIGN_PARENT_BOTTOM,
        ALIGN_PARENT_START,
        ALIGN_PARENT_END,
        CENTER_VERTICAL,
        CENTER_HORIZONTAL,
        TOP_OF,
        BOTTOM_OF,
        START_OF,
        END_OF,
        ALIGN_TOP,
        ALIGN_BOTTOM,
        ALIGN_START,
        ALIGN_END
    }

    val positions: MutableList<List<Pair<Positioning, Int>>> = mutableListOf()

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

    override fun addChild(child: View) = addChild(child, listOf(Positioning.ALIGN_PARENT_TOP to id))

    override fun removeChild(child: View): Boolean {
        val index = childViews.indexOf(child)
        if (index != -1) {
            positions.removeAt(index)
            return true
        }
        return false
    }
}