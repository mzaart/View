package view.core.loaders.builders.input.textInput

import view.core.loaders.builders.AbstractViewBuilder
import view.utils.mapBased.keys.delegates.nullable.ColorRWKey
import view.utils.mapBased.keys.delegates.nullable.IntRWKey
import view.utils.mapBased.keys.delegates.nullable.StringRWKey
import view.core.views.input.textInput.EditText
import view.utils.extensions.nonNull

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