package core.loaders.builders.input.textInput

import core.loaders.builders.ViewBuilder
import core.loaders.keys.delegates.nullable.ColorKey
import core.loaders.keys.delegates.nullable.IntKey
import core.loaders.keys.delegates.nullable.StringKey
import core.views.input.textInput.EditText
import utils.extensions.nonNull

class EditTextBuilder: ViewBuilder<EditText>() {

    override val view = EditText()

    private val text by StringKey()

    // style keys
    private val fontSize by IntKey()
    private val fontColor by ColorKey()

    override fun applyAttributes() {
        text.nonNull { view.text = it }

        fontSize.nonNull { view.style.fontSize = it }
        fontColor.nonNull { view.style.fontColor = it }
    }
}