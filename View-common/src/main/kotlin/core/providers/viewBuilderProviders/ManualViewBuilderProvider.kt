package core.providers.viewBuilderProviders

import core.loaders.builders.ViewBuilder
import core.views.View

class ManualViewBuilderProvider(
        private val builders: MutableMap<String, ViewBuilder<View>>
): ViewBuilderProvider {

    fun addBuilder(tag: String, builder: ViewBuilder<View>) {
        builders.keys.find { t -> t.equals(tag) } ?: throw IllegalArgumentException("Tage $tag already exists")
        builders.put(tag, builder)
    }

    override fun getBuilderInstance(tag: String): ViewBuilder<View> {
        val key = builders.keys.find { t -> t.equals(tag) }
        return builders[key] ?: throw IllegalArgumentException("No builder with tag $tag")
    }
}