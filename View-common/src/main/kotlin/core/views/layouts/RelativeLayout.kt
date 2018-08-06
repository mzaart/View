package core.views.layouts

import core.views.View

class RelativeLayout: Layout() {

    private val positions: MutableList<Pair<View, List<Positioning>>> = mutableListOf()

    enum class Positioning {
        ALIGN_PARENT_TOP,
        ALIGN_PARENT_BOTTOM,
        ALIGN_PARENT_START,
        ALIGN_PARENT_END,
        TOP_OF,
        BOTTOM_OF,
        START_OF,
        END_OF,
        ALIGN_TOP,
        ALIGN_BOTTOM,
        ALIGN_START,
        ALIGN_END
    }

    fun addChild(child: View, positionings: List<Positioning>, target: View) {
        positions.add(Pair(target, positionings))
        children.add(child)
    }

    override fun removeChild(child: View): Boolean {
        val index = children.indexOf(child)
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