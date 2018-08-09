package core.loaders.builders.display

import core.loaders.builders.ViewBuilder
import core.views.display.TextView

class TextViewBuilder: ViewBuilder<TextView>() {

    override val view = TextView()
}