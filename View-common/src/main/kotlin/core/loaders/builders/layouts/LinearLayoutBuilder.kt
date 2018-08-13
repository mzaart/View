package core.loaders.builders.layouts

import core.loaders.keys.delegates.required.RequiredEnumKey
import core.views.layouts.LinearLayout

open class LinearLayoutBuilder: LayoutBuilder<LinearLayout>() {

    private val direction by RequiredEnumKey<LinearLayout.Direction>()

    override val view = LinearLayout()

    override fun applyAttributes() {
        view.direction = direction
    }
}