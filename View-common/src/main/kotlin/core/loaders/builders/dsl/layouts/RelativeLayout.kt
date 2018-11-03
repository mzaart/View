package core.loaders.builders.dsl.layouts

import core.loaders.builders.layouts.RelativeLayoutBuilder
import core.views.View
import core.views.layouts.RelativeLayout


@Suppress("ClassName")
object relativeLayout {

    operator fun invoke(init: RelativeLayoutBuilder.() -> Unit): RelativeLayout {
       return RelativeLayoutBuilder().apply {
           init()
       }.build()
    }

    val RelativeLayoutBuilder.children
        get() = RelativeLayoutChildren(this)

    infix fun <V: View> V.relativeTo(init: RelativeLayoutBuilder.Child.() -> Unit):
            Pair<V, RelativeLayoutBuilder.Child> {
        val posKeys = RelativeLayoutBuilder.Child()
        posKeys.init()
        return Pair(this, posKeys)
    }
}


class RelativeLayoutChildren(private val bldr: RelativeLayoutBuilder) {

    operator fun get(vararg children: Pair<View, RelativeLayoutBuilder.Child>) {
        children.forEach { bldr.addChild(it.first, it.second.keys) }
    }
}
