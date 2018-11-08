package core.loaders.builders.display

import core.loaders.builders.AbstractViewBuilder
import utils.mapBased.keys.delegates.nullable.ColorRWKey
import utils.mapBased.keys.delegates.nullable.IntRWKey
import utils.mapBased.keys.delegates.nullable.StringRWKey
import core.views.display.TextView
import utils.extensions.nonNull

class TextViewBuilder: AbstractViewBuilder<TextView>() {

    var text by StringRWKey

    // style keys
    var fontSize by IntRWKey
    var fontColor by ColorRWKey

    override val view = TextView()

    override fun beforeProduction() {
        text.nonNull { view.text = it }

        fontSize.nonNull { view.fontSize = it }
        fontColor.nonNull { view.fontColor = it }
    }
}