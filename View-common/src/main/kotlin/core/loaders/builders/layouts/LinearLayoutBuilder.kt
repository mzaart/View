package core.loaders.builders.layouts

import utils.mapBased.keys.delegates.required.RequiredEnumRWKey
import core.views.layouts.LinearLayout

open class LinearLayoutBuilder: LayoutBuilder<LinearLayout>() {

    var direction by RequiredEnumRWKey(LinearLayout.Direction.values())

    override val view = LinearLayout()

    override fun beforeProduction() {
        super.beforeProduction()
        view.direction = direction
    }
}