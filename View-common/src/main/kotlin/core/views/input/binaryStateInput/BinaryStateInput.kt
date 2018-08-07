package core.views.input.binaryStateInput

import core.views.View
import core.views.events.BinaryStateInputEvents
import core.views.propertyDelegates.EventListener
import core.views.propertyDelegates.ViewProperty

abstract class BinaryStateInput: View() {

    var isOn: Boolean by ViewProperty(false)
    var onStateChangedListener by EventListener(BinaryStateInputEvents.ON_STATE_CHANGED)
}