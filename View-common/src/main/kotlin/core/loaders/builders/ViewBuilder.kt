package core.loaders.builders

import core.views.View

interface ViewBuilder<V: View> {

    fun build(): V
    fun applyKeys(keys: Map<String, Any>): ViewBuilder<V>
}