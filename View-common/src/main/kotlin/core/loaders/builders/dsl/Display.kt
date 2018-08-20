package core.loaders.builders.dsl

import core.loaders.builders.display.ImageViewBuilder
import core.loaders.builders.display.TextViewBuilder
import core.views.display.ImageView
import core.views.display.TextView

@Suppress("ClassName")
object textView {

    operator fun invoke(init: TextViewBuilder.() -> Unit): TextView {
        return TextViewBuilder().apply {
            init()
        }.build()
    }
}

@Suppress("ClassName")
object imageView {

    operator fun invoke(init: ImageViewBuilder.() -> Unit): ImageView {
        return ImageViewBuilder().apply {
            init()
        }.build()
    }
}