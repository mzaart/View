package core.views.display

import core.views.View
import core.views.propertyDelegates.NullableViewProperty
import core.views.propertyDelegates.ViewProperty
import utils.validators.Validator
import utils.validators.conditions.DC
import utils.validators.conditions.LC
import utils.validators.conditions.SC

class TextView: View() {

    enum class FontStyle {
        NORMAL,
        ITALIC,
        BOLD
    }

    enum class Align {
        LEFT,
        RIGHT,
        CENTER,
        JUSTIFY
    }

    var text: String? by NullableViewProperty()

    var align: Align by ViewProperty(Align.LEFT)
    var font: String? by NullableViewProperty(Validator(SC.PRESENT))
    var fontSize: Double? by NullableViewProperty(Validator(DC.POSITIVE))
    var fontColor: Long? by NullableViewProperty(Validator(LC.COLOR))
    var fontStyle: Set<FontStyle> by ViewProperty(setOf(FontStyle.NORMAL))
}