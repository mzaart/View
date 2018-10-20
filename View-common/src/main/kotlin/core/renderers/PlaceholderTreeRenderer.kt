package core.renderers

import core.views.events.Event
import core.views.events.EventListener

class PlaceholderTreeRenderer: ViewTreeRenderer {

    override fun invalidate(viewId: Int) {}

    override fun setEventListener(viewId: Int, event: Event, listener: EventListener) {}
}