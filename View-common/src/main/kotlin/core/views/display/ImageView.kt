package views.display

import core.renderers.ViewRenderer
import core.views.View

abstract class ImageView(
    id: Int,
    width: Double,
    height: Double,
    renderer: ViewRenderer
) : View(id, width, height, renderer)