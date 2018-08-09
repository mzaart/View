package core.loaders.builders.layouts

import core.loaders.builders.ViewBuilder
import core.views.View
import core.views.layouts.Layout

abstract class LayoutBuilder<L: Layout>: ViewBuilder<L>() {

    open fun addChild(child: View, content: Map<String, String>) {
        view.addChild(child)
    }
}