package view.core.loaders.builders.dsl.layouts

import view.core.loaders.builders.layouts.LinearLayoutBuilder
import view.core.views.View
import view.core.views.layouts.LinearLayout

@Suppress("ClassName")
object linearLayout {

    operator fun invoke(init: LinearLayoutBuilder.() -> Unit): LinearLayout {
        return LinearLayoutBuilder().apply {
            init()
        }.build()
    }

    val LinearLayoutBuilder.children: LayoutChildren
        get() = LayoutChildren(this)
}

class LayoutChildren(private val bldr: LinearLayoutBuilder) {

    operator fun get(vararg children: View) {
        children.forEach { bldr.addChild(it, mapOf()) }
    }
}