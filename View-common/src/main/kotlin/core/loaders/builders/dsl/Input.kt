package core.loaders.builders.dsl

import core.loaders.builders.input.binaryStateInput.CheckBoxBuilder
import core.loaders.builders.input.binaryStateInput.RadioButtonBuilder
import core.loaders.builders.input.binaryStateInput.SwitchBuilder
import core.loaders.builders.input.textInput.EditTextBuilder

@Suppress("ClassName")
object checkBox {

    operator fun invoke(init: CheckBoxBuilder.() -> Unit) = CheckBoxBuilder().apply { init() }.build()
}


@Suppress("ClassName")
object radioButton {

    operator fun invoke(init: RadioButtonBuilder.() -> Unit) = RadioButtonBuilder().apply { init() }.build()
}


@Suppress("ClassName")
object switch {

    operator fun invoke(init: SwitchBuilder.() -> Unit) = SwitchBuilder().apply { init() }.build()
}


@Suppress("ClassName")
object editText {

    operator fun invoke(init: EditTextBuilder.() -> Unit) = EditTextBuilder().apply { init() }.build()
}
