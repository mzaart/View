package core.views.display

import core.views.View
import core.views.propertyDelegates.NullableViewProperty
import core.views.style.viewSpecific.TextStyle

class TextView: View() {

    var text: String? by NullableViewProperty()

    override val style = TextStyle()
}