package core.loaders.builders

import core.views.View

/**
 * Defines an interface for view builders.
 */
interface ViewBuilder<V: View> {

    /**
     * Builds the view
     *
     * @return The constructed view
     */
    fun build(): V

    /**
     * Edits the views based on the passed keys.
     *
     * @param keys The keys used to extract information from.
     * @return The current builder instance
     */
    fun applyKeys(keys: Map<String, Any>): ViewBuilder<V>
}