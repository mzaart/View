package core.loaders.builders.input.binaryStateInput

import core.loaders.builders.AbstractViewBuilder
import core.views.input.binaryStateInput.Switch

class SwitchBuilder: AbstractViewBuilder<Switch>() {
    override val view = Switch()
}