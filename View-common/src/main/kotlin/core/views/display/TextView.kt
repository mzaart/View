package core.views.display

import core.views.View
import core.views.propertyDelegates.NullableViewProperty

class TextView: View() {

    var text: String? by NullableViewProperty()
}