package view.core.loaders.builders.input

import view.core.loaders.builders.AbstractViewBuilder
import view.utils.mapBased.keys.delegates.nullable.StringRWKey
import view.core.views.input.Button
import view.utils.extensions.nonNull
import view.utils.mapBased.keys.delegates.nullable.EnumRWKey

open class ButtonBuilder: AbstractViewBuilder<Button>() {

    override val view = Button()

    var type by EnumRWKey(Button.TYPE.values())
    var text by StringRWKey

    override fun beforeProduction() {
        type.nonNull { view.type = it }
        text.nonNull { view.text = it }
    }
}