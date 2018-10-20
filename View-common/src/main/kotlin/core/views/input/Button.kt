package core.views.input

import core.views.View
import core.views.propertyDelegates.NullableViewProperty
import core.views.propertyDelegates.ViewProperty

class Button: View() {

    enum class TYPE {
        NORMAL,
        ICON,
        PLATFORM_SPECIFIC
    }

    var type: TYPE by ViewProperty(TYPE.NORMAL)
    var text: String? by NullableViewProperty()
}