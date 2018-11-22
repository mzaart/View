package core.renderers

import core.views.View
import core.views.layouts.Layout

/**
 * Defines an interface for view tree renderers.
 */
interface ViewTreeRenderer {

    /**
     * Sets a layout as the root of the view tree.
     *
     * @param layout The root of the view tree
     */
    fun setRoot(layout: Layout)

    /**
     * Re-renders the passed view.
     *
     * @param view The view to re-render
     */
    fun invalidate(view: View)
}