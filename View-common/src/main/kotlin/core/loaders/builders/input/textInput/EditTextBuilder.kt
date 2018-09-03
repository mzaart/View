package core.loaders.builders.input.textInput

import core.loaders.builders.ViewBuilder
import core.loaders.keys.delegates.nullable.ColorKey
import core.loaders.keys.delegates.nullable.IntKey
import core.loaders.keys.delegates.nullable.StringKey
import core.views.input.textInput.EditText
import utils.extensions.nonNull

class EditTextBuilder: ViewBuilder<EditText>() {

    override val view = EditText()

    var text by StringKey

    // style keys
    var fontSize by IntKey
    var fontColor by ColorKey

    override fun beforeProduction() {
        text.nonNull { view.text = it }

        fontSize.nonNull { view.style.fontSize = it }
        fontColor.nonNull { view.style.fontColor = it }
    }
}