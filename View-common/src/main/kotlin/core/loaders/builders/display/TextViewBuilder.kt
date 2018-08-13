package core.loaders.builders.display

import core.loaders.builders.ViewBuilder
import core.loaders.keys.delegates.nullable.StringKey
import core.views.display.TextView
import utils.extensions.nonNull

class TextViewBuilder: ViewBuilder<TextView>() {

    private val text by StringKey()

    override val view = TextView()

    override fun applyAttributes() {
        text.nonNull { view.text = it }
    }
}