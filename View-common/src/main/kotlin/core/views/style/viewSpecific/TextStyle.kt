package core.views.style.viewSpecific

import core.views.style.Style
import core.views.style.StyleAttr
import utils.validators.Validator
import utils.validators.conditions.IC
import utils.validators.conditions.LC

class TextStyle: Style() {

    var fontSize: Int? by StyleAttr(Validator(IC.POSITIVE))

    var fontColor: Long? by StyleAttr(Validator(LC.COLOR))
}