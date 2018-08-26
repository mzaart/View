package core.views.style.viewSpecific

import core.views.style.Style
import core.views.style.StyleAttr
import utils.validators.Validator
import utils.validators.conditions.IC

class TextStyle: Style() {

    var fontSize: Int? by StyleAttr(Validator(IC.POSITIVE))

    var fontColor: Int? by StyleAttr(Validator(IC.COLOR))
}