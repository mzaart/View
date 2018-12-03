package view.core.loaders.builders.input.binaryStateInput

import view.core.loaders.builders.AbstractViewBuilder
import view.core.views.input.binaryStateInput.Switch

open class SwitchBuilder: AbstractViewBuilder<Switch>() {
    override val view = Switch()
}