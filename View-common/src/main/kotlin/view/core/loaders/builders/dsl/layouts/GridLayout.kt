package view.core.loaders.builders.dsl.layouts

import view.core.loaders.builders.layouts.GridLayoutBuilder
import view.core.views.View
import view.core.views.layouts.GridLayout

@Suppress("ClassName")
object gridLayout {

    operator fun invoke(init: GridLayoutBuilder.() -> Unit): GridLayout {
        return GridLayoutBuilder().apply {
            init()
        }.build()
    }

    val GridLayoutBuilder.children
        get() = GridLayoutChildren(this)

    infix fun <V: View> V.inCell(init: GridLayoutBuilder.CellKeys.() -> Unit): Pair<View, GridLayoutBuilder.CellKeys>
            = Pair(this, GridLayoutBuilder.CellKeys().apply(init))

    fun <V: View> V.toEnd(): Pair<View, GridLayoutBuilder.CellKeys>
            = Pair(this, GridLayoutBuilder.CellKeys())
}

class GridLayoutChildren(val builder: GridLayoutBuilder) {

    operator fun get(vararg children: Pair<View, GridLayoutBuilder.CellKeys>) {
        children.forEach { builder.addChild(it.first, it.second.keys) }
    }
}
