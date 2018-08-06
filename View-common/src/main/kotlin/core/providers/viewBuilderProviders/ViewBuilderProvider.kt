package core.providers.viewBuilderProviders

import core.loaders.builders.ViewBuilder
import core.views.View


interface ViewBuilderProvider {

    fun getBuilderInstance(tag: String): ViewBuilder<View>
}