package core.loaders.builders.dsl.layouts

import core.loaders.builders.layouts.ScrollLayoutBuilder
import core.views.layouts.LinearLayout

@Suppress("ClassName")
object scrollLayout {

    operator fun invoke(init: ScrollLayoutBuilder.() -> Unit): LinearLayout {
        return ScrollLayoutBuilder().apply {
            init()
            children.childViews.forEach { addChild(it, mapOf()) }
        }.build()
    }

    val ScrollLayoutBuilder.children
        get() = LayoutChildren()
}