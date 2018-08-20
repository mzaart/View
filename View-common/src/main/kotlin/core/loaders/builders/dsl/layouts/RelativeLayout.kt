

package core.loaders.builders.dsl.layouts

import core.loaders.builders.layouts.RelativeLayoutBuilder
import core.views.View
import core.views.layouts.RelativeLayout


@Suppress("ClassName")
object relativeLayout {

    operator fun invoke(init: RelativeLayoutBuilder.() -> Unit): RelativeLayout {
       return RelativeLayoutBuilder().apply {
           init()
           children.childViews.forEach { pair -> addChild(pair.first, pair.second.keys) }
       }.build()
    }

    val RelativeLayoutBuilder.children
        get() = RelativeLayoutChildren()

    infix fun <V: View> V.relativeTo(init: RelativeLayoutBuilder.Child.() -> Unit):
            Pair<V, RelativeLayoutBuilder.Child> {
        val posKeys = RelativeLayoutBuilder.Child()
        posKeys.init()
        return Pair(this, posKeys)
    }
}


class RelativeLayoutChildren {

    var childViews: List<Pair<View, RelativeLayoutBuilder.Child>> = listOf()

    operator fun get(vararg children: Pair<View, RelativeLayoutBuilder.Child>) {
        childViews = children.toList()
    }
}