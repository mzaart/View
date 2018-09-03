package core.views.propertyDelegates

import core.views.View
import core.views.events.Event
import core.views.events.EventListener
import kotlin.reflect.KProperty

class EventListener(
        private val event: Event,
        listener: EventListener = {}
): AbstractViewProperty<EventListener>(listener) {

    override operator fun setValue(thisRef: View, property: KProperty<*>, value: EventListener) {
        renderer.setEventListener(thisRef.id, event, value)
        this.value = value
    }
}