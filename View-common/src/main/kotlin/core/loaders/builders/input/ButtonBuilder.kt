package core.loaders.builders.input

import core.loaders.builders.ViewBuilder
import utils.mapBased.keys.delegates.nullable.StringRWKey
import core.views.input.Button
import utils.extensions.nonNull
import utils.mapBased.keys.delegates.nullable.EnumRWKey

class ButtonBuilder: ViewBuilder<Button>() {

    override val view = Button()

    var type by EnumRWKey(Button.TYPE.values())
    var text by StringRWKey

    override fun beforeProduction() {
        type.nonNull { view.type = it }
        text.nonNull { view.text = it }
    }
}