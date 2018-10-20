package core.views.display

import core.views.View
import core.views.propertyDelegates.NullableViewProperty
import utils.validators.Validator
import utils.validators.conditions.IC
import utils.validators.conditions.LC

class TextView: View() {

    var text: String? by NullableViewProperty()

    var fontSize: Int? by NullableViewProperty(Validator(IC.POSITIVE))
    var fontColor: Long? by NullableViewProperty(Validator(LC.COLOR))
}