package core.loaders.builders.layouts

import core.loaders.viewTree.IllegalViewTreeException
import core.loaders.keys.ViewKeys
import core.loaders.keys.delegates.nullable.BoolKey
import core.loaders.keys.delegates.nullable.StringKey
import core.views.layouts.RelativeLayout
import utils.extensions.nonNull
import utils.extensions.toID
import kotlin.reflect.KProperty

typealias RP = RelativeLayout.Positioning

class RelativeLayoutBuilder: LayoutBuilder<RelativeLayout>() {

     class Child: ViewKeys() {
        var alignParentTop by BoolKey()
        var alignParentBottom by BoolKey()
        var alignParentStart by BoolKey()
        var alignParentEnd by BoolKey()
        var center by BoolKey()
        var centerHorizontal by BoolKey()
        var centerVertical by BoolKey()

        var topOf by StringKey()
        var bottomOf by StringKey()
        var startOf by StringKey()
        var endOf by StringKey()

        override val conflictingKeys: Set<Set<KProperty<*>>> = setOf(
                setOf(::alignParentTop, ::alignParentBottom, ::topOf, ::bottomOf, ::centerVertical, ::center),
                setOf(::alignParentStart, ::alignParentEnd, ::startOf, ::endOf, ::centerHorizontal, ::center)
        )
    }

    override val view = RelativeLayout()
    
    override fun addChildren() {
        children.forEach { pair ->
            val pos: MutableList<Pair<RP, Int>> = mutableListOf()
            val keys = Child()
            keys.keys = pair.second.toMutableMap()

            keys.apply {
                alignParentTop.nonNull { pos += RP.ALIGN_PARENT_TOP to view.id }
                alignParentBottom.nonNull { pos += RP.ALIGN_PARENT_BOTTOM to view.id }
                alignParentStart.nonNull { pos += RP.ALIGN_PARENT_START to view.id }
                alignParentEnd.nonNull { pos += RP.ALIGN_PARENT_END to view.id }
                center.nonNull { pos += listOf(RP.CENTER_VERTICAL to view.id, RP.CENTER_HORIZONTAL to view.id) }
                centerHorizontal.nonNull { pos += RP.CENTER_HORIZONTAL to view.id }
                centerVertical.nonNull { pos += RP.CENTER_VERTICAL to view.id }

                topOf.nonNull { pos += RP.TOP_OF to assignId(it.toID()) }
                bottomOf.nonNull { pos += RP.BOTTOM_OF to assignId(it.toID()) }
                startOf.nonNull { pos += RP.START_OF to assignId(it.toID()) }
                endOf.nonNull { pos += RP.END_OF to assignId(it.toID()) }
            }

            view.addChild(pair.first, pos)
        }

    }

    private fun assignId(id: Int) = if (view.children().any { id == it.id }) id
            else throw IllegalViewTreeException("Positioning DslView relative to a non-existent DslView")
}