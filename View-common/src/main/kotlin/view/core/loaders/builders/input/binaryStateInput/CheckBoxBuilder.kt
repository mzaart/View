package view.core.loaders.builders.input.binaryStateInput

import view.core.loaders.builders.AbstractViewBuilder
import view.core.views.input.binaryStateInput.CheckBox

open class CheckBoxBuilder: AbstractViewBuilder<CheckBox>() {

    override val view = CheckBox()
}