package core.views.display

import core.views.View
import core.views.propertyDelegates.NullableViewProperty
import core.views.propertyDelegates.ViewProperty
import utils.validators.Validator
import utils.validators.conditions.DC
import utils.validators.conditions.LC
import utils.validators.conditions.SC

/**
 * Represents a block of text on the UI.
 */
class TextView: View() {

    /**
     * The text's font style.
     */
    enum class FontStyle {
        NORMAL,
        ITALIC,
        BOLD
    }

    /**
     * Specifies how the text is aligned in the view.
     */
    enum class Align {
        LEFT,
        RIGHT,
        CENTER,
        JUSTIFY
    }

    /**
     * The text to be displayed.
     */
    var text: String? by NullableViewProperty()

    /**
     * Specifies how the text is aligned in the view.
     *
     * The default value of this property is [Align.LEFT]
     */
    var align: Align by ViewProperty(Align.LEFT)

    /**
     * Specifies the text's font.
     */
    var font: String? by NullableViewProperty(Validator(SC.PRESENT))

    /**
     * Specifies the text's font size.
     */
    var fontSize: Double? by NullableViewProperty(Validator(DC.POSITIVE))

    /**
     * Specifies the text's font color.
     */
    var fontColor: Long? by NullableViewProperty(Validator(LC.COLOR))

    /**
     * Specifies the text's font style.
     *
     * The default value of this property is [FontStyle.NORMAL]
     */
    var fontStyle: Set<FontStyle> by ViewProperty(setOf(FontStyle.NORMAL))
}