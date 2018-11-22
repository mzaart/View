package core.views.input

import core.views.View
import core.views.propertyDelegates.NullableViewProperty
import core.views.propertyDelegates.ViewProperty

/**
 * Represents a button.
 */
class Button: View() {

    /**
     * Represents a button type.
     */
    enum class TYPE {

        /**
         * It is the responsibility of the [ViewTreeRenderer][core.renderers.ViewTreeRenderer] to define what a 'Normal'
         * button should look like.
         */
        NORMAL,

        /**
         * Defines an icon button.
         */
        ICON,

        /**
         * Defines a button type that is specific to a certain platform.
         *
         * Further information on the button type can be passed [ViewTreeRenderer][core.renderers.ViewTreeRenderer]
         * using view extras.
         *
         * @see View.webExtras
         * @see View.androidExtras
         * @see View.iosExtras
         */
        PLATFORM_SPECIFIC
    }

    /**
     * Specifies the button type.
     *
     * The value of this property is [Button.TYPE.NORMAL] by default.
     */
    var type: TYPE by ViewProperty(TYPE.NORMAL)

    /**
     * Specifies the button's label.
     */
    var text: String? by NullableViewProperty()
}