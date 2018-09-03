package core.views.propertyDelegates

import core.renderers.ViewRenderer
import core.views.events.Event
import core.views.events.EventListener
import kotlin.test.assertEquals

class MockRenderer(
        private val expectedId: Int,
        private val expectedEvent: Event? = null,
        private val expectedEventListener: EventListener? = null
): ViewRenderer {

    override fun invalidate(viewId: Int) {
       assertEquals(expectedId, viewId)
    }

    override fun setEventListener(viewId: Int, event: Event, listener: EventListener) {
        if (expectedEvent == null || expectedEventListener == null) {
            throw IllegalStateException()
        }
        assertEquals(expectedId, viewId)
        assertEquals(expectedEvent, event)
        assertEquals(expectedEventListener, listener)
    }
}