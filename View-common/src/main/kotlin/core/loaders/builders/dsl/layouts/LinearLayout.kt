package core.loaders.builders.dsl.layouts

import core.loaders.builders.layouts.LinearLayoutBuilder
import core.views.View
import core.views.layouts.LinearLayout

@Suppress("ClassName")
object linearLayout {

    operator fun invoke(init: LinearLayoutBuilder.() -> Unit): LinearLayout {
        return LinearLayoutBuilder().apply {
            init()
            children.childViews.forEach { addChild(it, mapOf()) }
        }.build()
    }

    val LinearLayoutBuilder.children
        get() = LayoutChildren()
}

class LayoutChildren {

    var childViews: List<View> = listOf()

    operator fun get(vararg children: View) {
        childViews = children.toList()
    }
}