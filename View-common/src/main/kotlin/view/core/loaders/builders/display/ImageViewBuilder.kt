package view.core.loaders.builders.display

import view.core.loaders.builders.AbstractViewBuilder
import view.core.views.display.ImageView
import view.utils.mapBased.keys.delegates.nullable.EnumRWKey
import view.utils.mapBased.keys.delegates.required.RequiredStringRWKey

open class ImageViewBuilder: AbstractViewBuilder<ImageView>() {

    override val view = ImageView()

    var imageResource by RequiredStringRWKey
    var scaleType by EnumRWKey(ImageView.ScaleType.values())

    override fun beforeProduction() {

        view.imageResource = imageResource
        view.scaleType = this.scaleType ?: ImageView.ScaleType.CENTER
    }
}