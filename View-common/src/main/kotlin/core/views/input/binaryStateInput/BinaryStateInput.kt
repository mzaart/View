package core.views.input.binaryStateInput

import core.views.View
import core.views.propertyDelegates.NullableViewProperty
import core.views.propertyDelegates.ViewProperty

abstract class BinaryStateInput: View() {

    var isOn: Boolean by ViewProperty(false)
    var onStateChangedListener by NullableViewProperty<(BinaryStateInput) -> Unit>()
}