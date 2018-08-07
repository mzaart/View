package core.views.input.binaryStateInput

import core.views.propertyDelegates.NullableViewProperty

class RadioButton : BinaryStateInput() {

    var groupId: Int? by NullableViewProperty()
}