package core.loaders.builders.layouts

import core.loaders.IllegalViewTreeException
import core.loaders.keys.ViewKeys
import core.loaders.keys.delegates.nullable.BoolKey
import core.loaders.keys.delegates.nullable.IdKey
import core.views.layouts.RelativeLayout
import utils.extensions.nonNull
import kotlin.reflect.KProperty

typealias RP = RelativeLayout.Positioning

class RelativeLayoutBuilder: LayoutBuilder<RelativeLayout>() {

    private class RelativeLayoutChild: ViewKeys() {
        val alignParentTop by BoolKey()
        val alignParentBottom by BoolKey()
        val alignParentStart by BoolKey()
        val alignParentEnd by BoolKey()
        val center by BoolKey()
        val centerHorizontal by BoolKey()
        val centerVertical by BoolKey()

        val topOf by IdKey()
        val bottomOf by IdKey()
        val startOf by IdKey()
        val endOf by IdKey()

        override val conflictingKeys: Set<Set<KProperty<*>>> = setOf(
                setOf(::alignParentTop, ::alignParentBottom, ::topOf, ::bottomOf, ::centerVertical, ::center),
                setOf(::alignParentStart, ::alignParentEnd, ::startOf, ::endOf, ::centerHorizontal, ::center)
        )
    }

    override val view = RelativeLayout()
    
    override fun addChildren() {
        children.forEach { pair ->
            val pos: MutableList<Pair<RP, Int>> = mutableListOf()
            val keys = RelativeLayoutChild()
            keys.keys = pair.second

            keys.apply {
                alignParentTop.nonNull { pos += RP.ALIGN_PARENT_TOP to view.id }
                alignParentBottom.nonNull { pos += RP.ALIGN_PARENT_BOTTOM to view.id }
                alignParentStart.nonNull { pos += RP.ALIGN_PARENT_START to view.id }
                alignParentEnd.nonNull { pos += RP.ALIGN_PARENT_END to view.id }
                center.nonNull { pos += listOf(RP.CENTER_VERTICAL to view.id, RP.CENTER_HORIZONTAL to view.id) }
                centerHorizontal.nonNull { pos += RP.CENTER_HORIZONTAL to view.id }
                centerVertical.nonNull { pos += RP.CENTER_VERTICAL to view.id }

                topOf.nonNull { pos += RP.TOP_OF to assignId(it) }
                bottomOf.nonNull { pos += RP.BOTTOM_OF to assignId(it) }
                startOf.nonNull { pos += RP.START_OF to assignId(it) }
                endOf.nonNull { pos += RP.END_OF to assignId(it) }
            }

            view.addChild(pair.first, pos)
        }

    }

    private fun assignId(id: Int) = if (view.children().any { id == it.id }) id
            else throw IllegalViewTreeException("Positioning View relative to a non-existent View")
}