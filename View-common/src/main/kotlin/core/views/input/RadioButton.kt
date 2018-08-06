package views.input

import core.renderers.ViewRenderer

class RadioButton(
        id: Int,
        width: Double,
        height: Double,
        renderer: ViewRenderer,
        groupId: Int
) : BinaryStateInput(id, width, height, renderer)