package view.core.loaders.builders.layouts

import view.core.loaders.viewTree.IllegalViewTreeException
import view.core.loaders.builders.ViewKeys
import view.utils.mapBased.keys.delegates.nullable.BoolRWKey
import view.utils.mapBased.keys.delegates.nullable.StringRWKey
import view.core.views.layouts.RelativeLayout
import view.utils.extensions.nonNull
import view.utils.extensions.toID

typealias RP = RelativeLayout.Positioning

open class RelativeLayoutBuilder: LayoutBuilder<RelativeLayout>() {

     class Child: ViewKeys() {
         var alignParentTop by BoolRWKey
         var alignParentBottom by BoolRWKey
         var alignParentStart by BoolRWKey
         var alignParentEnd by BoolRWKey
         var center by BoolRWKey
         var centerHorizontal by BoolRWKey
         var centerVertical by BoolRWKey

         var topOf by StringRWKey
         var bottomOf by StringRWKey
         var startOf by StringRWKey
         var endOf by StringRWKey

         var alignTop by StringRWKey
         var alignBottom by StringRWKey
         var alignStart by StringRWKey
         var alignEnd by StringRWKey
    }

    override val view = RelativeLayout()

    override fun addChildrenToViews() {
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

                alignTop.nonNull { pos += RP.ALIGN_TOP to assignId(it.toID()) }
                alignBottom.nonNull { pos += RP.ALIGN_BOTTOM to assignId(it.toID()) }
                alignStart.nonNull { pos += RP.ALIGN_START to assignId(it.toID()) }
                alignEnd.nonNull { pos += RP.ALIGN_END to assignId(it.toID()) }
            }

            view.addChild(pair.first, pos)
        }
    }

    private fun assignId(id: Int) = if (view.children().any { id == it.id }) id
            else throw IllegalViewTreeException("Positioning View relative to a non-existent View")
}