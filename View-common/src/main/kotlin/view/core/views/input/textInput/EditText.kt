package view.core.views.input.textInput

import view.core.views.View
import view.core.views.propertyDelegates.NullableViewProperty
import view.utils.validators.Validator
import view.utils.validators.conditions.IC
import view.utils.validators.conditions.LC

/**
 * Represents an editable text field.
 */
class EditText: View() {

    /**
     * The text currently in the text field.
     */
    var text: String? by NullableViewProperty()

    /**
     * A listener that gets invoked whenever the text changes.
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