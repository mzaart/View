package core.loaders.builders.layouts

import core.loaders.InvalidViewTreeException
import core.loaders.Key
import core.views.View
import core.views.layouts.RelativeLayout
import utils.lowerUnderscoreIsEnum
import utils.lowerUnderscoreToEnum

typealias RK = RelativeLayoutBuilder.Keys

class RelativeLayoutBuilder: LayoutBuilder<RelativeLayout>() {

    enum class Keys: Key {
        ALIGN_PARENT_TOP,
        ALIGN_PARENT_BOTTOM,
        ALIGN_PARENT_START,
        ALIGN_PARENT_END,
        TOP_OF,
        BOTTOM_OF,
        START_OF,
        END_OF,
        CENTER_HORIZONTAL,
        CENTER_VERTICAL
    }

    override val view = RelativeLayout()
    private val conflictingKeys: List<List<RK>> = listOf(
            listOf(RK.TOP_OF, RK.BOTTOM_OF, RK.ALIGN_PARENT_TOP, RK.ALIGN_PARENT_BOTTOM, RK.CENTER_VERTICAL),
            listOf(RK.ALIGN_PARENT_START, RK.ALIGN_PARENT_END, RK.START_OF, RK.END_OF, RK.CENTER_HORIZONTAL)
    )

    override fun addChild(child: View, content: Map<String, String>) {
        val rKeys = content.keys.filter { k -> k.lowerUnderscoreIsEnum<RK>() }
                .map { k -> k.lowerUnderscoreToEnum<RK>() }

        // check for conflicting keys
        for (i in 0 until rKeys.size) {
            for (j in i+1 until rKeys.size) {
                val l1 = conflictingKeys.first {list ->  list.contains(rKeys[i])}
                val l2 = conflictingKeys.first {list ->  list.contains(rKeys[j])}
                if (l1 == l2) {
                    throw InvalidViewTreeException(
                            "RelativeLayout Keys ${rKeys[i]} and ${rKeys[j]} can not exist together")
                }
            }
        }

        view.addChild(view)
    }
}