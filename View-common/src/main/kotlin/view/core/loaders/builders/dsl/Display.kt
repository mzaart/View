package view.core.loaders.builders.dsl

import view.core.loaders.builders.display.ImageViewBuilder
import view.core.loaders.builders.display.TextViewBuilder
import view.core.views.display.ImageView
import view.core.views.display.TextView

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