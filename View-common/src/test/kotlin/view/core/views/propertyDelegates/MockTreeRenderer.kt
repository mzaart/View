package view.core.views.propertyDelegates

import view.core.renderers.ViewTreeRenderer

import kotlin.test.assertEquals

class MockTreeRenderer(
        private val expectedId: Int,
        private val expectedEvent: Event? = null,
        private val expectedEventListener: EventListener? = null
): ViewTreeRenderer {

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