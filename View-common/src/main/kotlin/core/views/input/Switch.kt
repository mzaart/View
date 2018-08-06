package views.input

import core.renderers.ViewRenderer

class Switch(
        id: Int,
        width: Double,
        height: Double,
        renderer: ViewRenderer
) : BinaryStateInput(id, width, height, renderer)