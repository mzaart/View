package view.core.views.display

import view.core.views.View
import view.core.views.propertyDelegates.NullableViewProperty
import view.core.views.propertyDelegates.ViewProperty
import view.utils.validators.Validator
import view.utils.validators.conditions.DC
import view.utils.validators.conditions.LC
import view.utils.validators.conditions.SC

/**
 * Represents a block of text on the UI.
 */
open class TextView: View() {

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
    open var text: String? by NullableViewProperty()

    /**
     * Specifies how the text is aligned in the view.
     *
     * The default value of this property is [Align.LEFT]
     */
    open var align: Align by ViewProperty(Align.LEFT)

    /**
     * Specifies the text's font.
     */
    open var font: String? by NullableViewProperty(Validator(SC.PRESENT))

    /**
     * Specifies the text's font size.
     */
    open var fontSize: Double? by NullableViewProperty(Validator(DC.POSITIVE))

    /**
     * Specifies the text's font color.
     */
    open var fontColor: Long? by NullableViewProperty(Validator(LC.COLOR))

    /**
     * Specifies the text's font style.
     *
     * The default value of this property is [FontStyle.NORMAL]
     */
    open var fontStyle: Set<FontStyle> by ViewProperty(setOf(FontStyle.NORMAL))
}