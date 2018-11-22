package core.loaders.viewTree.treeLoaders

import core.views.layouts.Layout

/**
 * Defines an interface for view tree loaders
 */
interface ViewTreeLoader {

    /**
     * Loads the view tree
     *
     * @return The root of the loaded view tree
     */
    fun loadViewTree(): Layout
}