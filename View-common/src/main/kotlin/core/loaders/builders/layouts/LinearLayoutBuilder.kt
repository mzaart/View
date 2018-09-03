package core.loaders.builders.layouts

import core.loaders.keys.delegates.required.RequiredEnumKey
import core.views.layouts.LinearLayout

open class LinearLayoutBuilder: LayoutBuilder<LinearLayout>() {

    var direction by RequiredEnumKey(LinearLayout.Direction.values())

    override val view = LinearLayout()

    override fun beforeProduction() {
        super.beforeProduction()
        view.direction = direction
    }
}