package view.core.views.input.binaryStateInput

import view.core.views.View
import view.core.views.propertyDelegates.NullableViewProperty
import view.core.views.propertyDelegates.ViewProperty

/**
 * This is the base class for views that represent input UI components with an on/off state.
 */
abstract class BinaryStateInput: View() {

    /**
     * Specifies whether the view in on or off.
     *
     * The value of this property is false by default.
     */
    var isOn: Boolean by ViewProperty(false)

    /**
     * A listener that gets invoked whenever the on/off state if the view is changed.
     */
    var onStateChangedListener by NullableViewProperty<(BinaryStateInput) -> Unit>()
}