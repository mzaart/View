package core.loaders.builders.display

import core.loaders.builders.AbstractViewBuilder
import core.views.display.TextView
import utils.extensions.nonNull
import utils.extensions.toEnum
import utils.mapBased.keys.delegates.nullable.*

class TextViewBuilder: AbstractViewBuilder<TextView>() {

    var text by StringRWKey

    // style keys
    var align by EnumRWKey(TextView.Align.values())
    var font by StringRWKey
    var fontSize by DoubleRWKey
    var fontColor by ColorRWKey
    var fontStyle by NullableRWKey<Set<TextView.FontStyle>> { str ->
        str.split(' ').mapTo(mutableSetOf()) { it.toEnum(TextView.FontStyle.values()) }
    }

    override val view = TextView()

    override fun beforeProduction() {
        text.nonNull { view.text = it }

        align.nonNull { view.align = it }
        font.nonNull { view.font = it }
        fontSize.nonNull { view.fontSize = it }
        fontColor.nonNull { view.fontColor = it }
        fontStyle.nonNull { view.fontStyle = it }
    }
}