package views.input

import core.renderers.ViewRenderer

class CheckBox(
        id: Int,
        width: Double,
        height: Double,
        renderer: ViewRenderer
) : BinaryStateInput(id, width, height, renderer)