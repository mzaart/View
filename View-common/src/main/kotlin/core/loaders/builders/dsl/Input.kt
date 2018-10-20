package core.loaders.builders.dsl

import core.loaders.builders.input.ButtonBuilder
import core.loaders.builders.input.binaryStateInput.CheckBoxBuilder
import core.loaders.builders.input.binaryStateInput.RadioButtonBuilder
import core.loaders.builders.input.binaryStateInput.SwitchBuilder
import core.loaders.builders.input.textInput.EditTextBuilder

object button {

    operator fun invoke(init: ButtonBuilder.() -> Unit) = ButtonBuilder().apply { init() }.build()
}

object checkBox {

    operator fun invoke(init: CheckBoxBuilder.() -> Unit) = CheckBoxBuilder().apply { init() }.build()
}


object radioButton {

    operator fun invoke(init: RadioButtonBuilder.() -> Unit) = RadioButtonBuilder().apply { init() }.build()
}


object switch {

    operator fun invoke(init: SwitchBuilder.() -> Unit) = SwitchBuilder().apply { init() }.build()
}


object editText {

    operator fun invoke(init: EditTextBuilder.() -> Unit) = EditTextBuilder().apply { init() }.build()
}
