package view.core.loaders.builders.layouts

import view.core.loaders.builders.AbstractViewBuilder
import view.core.views.View
import view.core.views.layouts.Layout
import view.utils.extensions.nonNull
import view.utils.mapBased.keys.delegates.nullable.BoolRWKey

/**
 * Base builder for layout builders.
 *
 * This builder sets properties common to all layouts.
 */
abstract class LayoutBuilder<L: Layout>: AbstractViewBuilder<L>() {

    var scrollX by BoolRWKey
    var scrollY by BoolRWKey

    /**
     * A list of child views with their corresponding position information
     */
    protected val children: MutableList<Pair<View, Map<String, Any?>>> = mutableListOf()

    /**
     * Adds the child to the builder. The child will be added to the layout once it is built.
     *
     * @param child The child view to add
     * @param childKeys Key-value pairs that contain information on the child's position in the layout
     */
    fun addChild(child: View, childKeys: Map<String, Any?> = mapOf()): LayoutBuilder<L> {
        children += child to childKeys
        return this
    }

    override fun beforeProduction() {
        scrollX.nonNull { view.scrollX = it }
        scrollY.nonNull { view.scrollY = it }
        addChildrenToViews()
    }

    /**
     * Adds the child views to the layout.
     */
    protected open fun addChildrenToViews() {
        children.forEach { view.addChild(it.first) }
    }
}