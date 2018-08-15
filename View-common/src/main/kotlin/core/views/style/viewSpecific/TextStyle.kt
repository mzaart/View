package core.views.style.viewSpecific

import core.views.propertyDelegates.NullableViewProperty
import core.views.style.Style
import core.views.style.StyleAttr
import utils.validators.Validator
import utils.validators.conditions.IC

class TextStyle: Style() {

    @StyleAttr
    var fontSize: Int? by NullableViewProperty(Validator(IC.POSITIVE))

    @StyleAttr
    var fontColor: Int? by NullableViewProperty(Validator(IC.COLOR))
}