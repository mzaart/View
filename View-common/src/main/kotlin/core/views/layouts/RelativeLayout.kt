package core.views.layouts

import core.views.View

class RelativeLayout: Layout() {

    private val positions: MutableList<Pair<Positioning, Int>> = mutableListOf()

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
        END_OF
    }

    fun addChild(child: View, positionings: List<Pair<Positioning, Int>>): Boolean {
        positions += positionings
        child.style.extendStyle(style)
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

    override fun removeChild(index: Int): View {
        positions.removeAt(index)
        return super.removeChild(index)
    }
}