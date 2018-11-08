package core.loaders.builders.display

import core.loaders.builders.AbstractViewBuilder
import core.views.display.ImageView

class ImageViewBuilder: AbstractViewBuilder<ImageView>() {

    override val view = ImageView()
}