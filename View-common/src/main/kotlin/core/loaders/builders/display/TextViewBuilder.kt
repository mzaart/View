package core.loaders.builders.display

import core.loaders.builders.ViewBuilder
import core.loaders.keys.delegates.nullable.ColorKey
import core.loaders.keys.delegates.nullable.IntKey
import core.loaders.keys.delegates.nullable.StringKey
import core.views.display.TextView
import utils.extensions.nonNull

class TextViewBuilder: ViewBuilder<TextView>() {

    var text by StringKey

    // style keys
    var fontSize by IntKey
    var fontColor by ColorKey

    override val view = TextView()

    override fun beforeProduction() {
        text.nonNull { view.text = it }

        fontSize.nonNull { view.style.fontSize = it }
        fontColor.nonNull { view.style.fontColor = it }
    }
}