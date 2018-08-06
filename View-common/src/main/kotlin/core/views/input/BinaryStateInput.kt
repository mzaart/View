package views.input

import core.renderers.ViewRenderer
import core.views.View

abstract class BinaryStateInput(
        id: Int,
        width: Double,
        height: Double,
        renderer: ViewRenderer
) : View(id, width, height, renderer) {

    var isOn: Boolean = false
    var onStateChangedListener: (Boolean) -> Unit = {}

    fun stateChanged(isOn: Boolean) = onStateChangedListener(isOn)
}