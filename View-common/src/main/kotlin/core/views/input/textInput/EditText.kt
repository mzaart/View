package core.views.input.textInput

import core.views.View
import core.views.propertyDelegates.NullableViewProperty
import utils.validators.Validator
import utils.validators.conditions.IC
import utils.validators.conditions.LC

/**
 * Represents an editable text field.
 */
class EditText: View() {

    /**
     * The text currently in the the text field.
     */
    var text: String? by NullableViewProperty()

    /**
     * A a listener that gets invoked whenever the text changes.
     */
    var onTextChanged by NullableViewProperty<(EditText) -> Unit>()

    /**
     * Specifies the font size of the text.
     */
    var fontSize: Int? by NullableViewProperty(Validator(IC.POSITIVE))

    /**
     * Specifies the color of the text.
     */
    var fontColor: Long? by NullableViewProperty(Validator(LC.COLOR))
}