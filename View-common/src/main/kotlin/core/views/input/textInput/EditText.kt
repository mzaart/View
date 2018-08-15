package core.views.input.textInput

import core.views.View
import core.views.events.TextInputEvents
import core.views.propertyDelegates.EventListener
import core.views.propertyDelegates.NullableViewProperty
import core.views.style.viewSpecific.TextStyle

class EditText: View() {

    var text: String? by NullableViewProperty()
    var onTextChanged by EventListener(TextInputEvents.ON_TEXT_CHANGED)

    override val style = TextStyle()
}