package views.display

import core.renderers.ViewRenderer
import core.views.View

class TextView(
        id: Int,
        width: Double,
        height: Double,
        renderer: ViewRenderer,
        var text: String? = null
) : View(id, width, height, renderer)