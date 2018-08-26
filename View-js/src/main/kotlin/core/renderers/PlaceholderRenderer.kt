package core.renderers

import core.views.events.Event
import core.views.events.EventListener

class PlaceholderRenderer: ViewRenderer {

    override fun invalidate(viewId: Int) {}

    override fun setEventListener(viewId: Int, event: Event, listener: EventListener) {}
}