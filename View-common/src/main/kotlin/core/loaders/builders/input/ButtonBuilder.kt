package core.loaders.builders.input

import core.loaders.builders.ViewBuilder
import utils.mapBased.keys.delegates.nullable.StringRWKey
import utils.mapBased.keys.delegates.required.RequiredEnumRWKey
import core.views.input.Button
import utils.extensions.nonNull

class ButtonBuilder: ViewBuilder<Button>() {

    override val view = Button()

    var type by RequiredEnumRWKey(Button.TYPE.values())
    var text by StringRWKey

    override fun beforeProduction() {
        view.type = type
        text.nonNull { view.text = it }
    }
}