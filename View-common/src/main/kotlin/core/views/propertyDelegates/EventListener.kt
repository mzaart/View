package core.views.propertyDelegates

import core.views.HasId
import core.views.View
import core.views.events.Event
import core.views.events.EventListener
import kotlin.reflect.KProperty

class EventListener(
        private val event: Event,
        private var listener: EventListener = {}
): AbstractViewProperty<EventListener>(listener) {

    override operator fun setValue(thisRef: HasId, property: KProperty<*>, value: EventListener) {
        renderer.setEventListener(thisRef.id, event, value)
        listener = value
    }
}