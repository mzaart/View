package core.views.input.textInput

import core.views.View
import core.views.propertyDelegates.NullableViewProperty
import utils.validators.Validator
import utils.validators.conditions.IC
import utils.validators.conditions.LC

class EditText: View() {

    var text: String? by NullableViewProperty()
    var onTextChanged by NullableViewProperty<(EditText) -> Unit>()

    var fontSize: Int? by NullableViewProperty(Validator(IC.POSITIVE))
    var fontColor: Long? by NullableViewProperty(Validator(LC.COLOR))
}