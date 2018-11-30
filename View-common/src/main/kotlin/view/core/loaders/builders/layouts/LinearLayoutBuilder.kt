package view.core.loaders.builders.layouts

import view.utils.mapBased.keys.delegates.required.RequiredEnumRWKey
import view.core.views.layouts.LinearLayout

open class LinearLayoutBuilder: LayoutBuilder<LinearLayout>() {

    var direction by RequiredEnumRWKey(LinearLayout.Direction.values())

    override val view = LinearLayout()

    override fun beforeProduction() {
        super.beforeProduction()
        view.direction = direction
    }
}