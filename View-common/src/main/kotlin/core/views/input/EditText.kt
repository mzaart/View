package views.input

import core.renderers.ViewRenderer
import core.views.View
import core.views.viewDelegates.ViewProperty

class EditText(
    id: Int,
    width: Double,
    height: Double,
    renderer: ViewRenderer
) : View(id, width, height, renderer) {

    var text: String by ViewProperty("")
}