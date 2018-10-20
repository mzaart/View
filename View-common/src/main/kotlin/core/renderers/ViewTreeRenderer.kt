package core.renderers

import core.views.events.Event
import core.views.events.EventListener

interface ViewTreeRenderer {

    fun invalidate(viewId: Int)
    fun setEventListener(viewId: Int, event: Event, listener: EventListener)
}