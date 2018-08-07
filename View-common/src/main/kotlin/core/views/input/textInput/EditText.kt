package core.views.input.textInput

import core.views.View
import core.views.events.TextInputEvents
import core.views.propertyDelegates.EventListener
import core.views.propertyDelegates.NullableViewProperty

class EditText: View() {

    var text: String? by NullableViewProperty()
    var onTextChanged by EventListener(TextInputEvents.ON_TEXT_CHANGED)
}