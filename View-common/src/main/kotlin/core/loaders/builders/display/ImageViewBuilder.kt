package core.loaders.builders.display

import core.loaders.builders.AbstractViewBuilder
import core.views.display.ImageView
import utils.mapBased.keys.delegates.nullable.EnumRWKey
import utils.mapBased.keys.delegates.required.RequiredStringRWKey

class ImageViewBuilder: AbstractViewBuilder<ImageView>() {

    override val view = ImageView()

    var imageResource by RequiredStringRWKey
    var scaleType by EnumRWKey(ImageView.ScaleType.values())

    override fun beforeProduction() {

        view.imageResource = imageResource
        view.scaleType = this.scaleType ?: ImageView.ScaleType.CENTER
    }
}