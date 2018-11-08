package core.loaders.builders.input.textInput

import core.loaders.builders.AbstractViewBuilder
import utils.mapBased.keys.delegates.nullable.ColorRWKey
import utils.mapBased.keys.delegates.nullable.IntRWKey
import utils.mapBased.keys.delegates.nullable.StringRWKey
import core.views.input.textInput.EditText
import utils.extensions.nonNull

class EditTextBuilder: AbstractViewBuilder<EditText>() {

    override val view = EditText()

    var text by StringRWKey

    // style keys
    var fontSize by IntRWKey
    var fontColor by ColorRWKey

    override fun beforeProduction() {
        text.nonNull { view.text = it }

        fontSize.nonNull { view.fontSize = it }
        fontColor.nonNull { view.fontColor = it }
    }
}