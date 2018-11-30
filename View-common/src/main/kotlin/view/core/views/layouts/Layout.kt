package view.core.views.layouts

import view.core.renderers.ViewTreeRenderer
import view.core.views.View
import view.core.views.propertyDelegates.ViewProperty
import view.di.inject
import view.utils.extensions.toID
import view.utils.observables.ObservableCollection

/**
 * This is the base class for layout views, which are views that contain other views.
 *
 * All non-leaf nodes of a view tree should be an instance of a subclass of the Layout class. This class implements
 * only basic operations common to all layouts which are adding and removing child views, the logic needed for
 * positioning child views is to be implemented by subclasses.
 */
abstract class Layout: View() {

    private val renderer by inject<ViewTreeRenderer>()

    /**
     * Determines if the layout can be scrolled horizontally.
     */
    var scrollX: Boolean by ViewProperty(false)

    /**
     * Determines if the layout can be scrolled vertically.
     */
    var scrollY: Boolean by ViewProperty(false)

    /**
     * A mutable collection containing the child views.
     *
     * It is implemented using the [ObservableCollection] property delegate which notifies the [ViewTreeRenderer]
     * whenever a child is added or removed.
     */
    protected val childViews: MutableCollection<View> = ObservableCollection { renderer.invalidate(this) }

    /**
     * Exposes the child views.
     *
     * @return List containing the child views.
     */
    fun children() = childViews.toList()

    /**
     * Returns the direct or indirect child view having the specified ID.
     *
     * The String representation of the ID is mapped to the view's ID using the [String.toID] extension method.
     * Internally, this method searches for the child view using the Breadth-First Search algorithm.
     *
     * @param id String representation of the ID
     * @return The direct or indirect child having the specified ID.
     *
     * @throws NoSuchElementException If no child view was found with the specified ID.
     */
    fun find(id: String) = find(id.toID())

    /**
     * Returns the direct or indirect child view having the specified ID.
     *
     * Internally, this method searches for the child view using the Breadth-First Search algorithm.
     *
     * @param id The view's ID
     * @return The direct or indirect child having the specified ID.
     *
     * @throws NoSuchElementException If no child view was found with the specified ID.
     */
    fun find(id: Int): View {
        val queue = children().toMutableList()
        while (!queue.isEmpty()) {
            val view = queue.removeAt(0)
            if (view.id == id) {
                return view
            }
            if (view is Layout) {
                queue += view.children()
            }
        }
        throw NoSuchElementException("No View exists for the given ID")
    }

    /**
     * Adds a child view to the layout.
     *
     * When the child is added, the layout would be re-rendered.
     *
     * @param child The view to be added.
     * @return True if the child was added
     */
    open fun addChild(child: View): Boolean {
        val added = childViews.add(child)
        if (added) {
            child.parent = this
        }
        return added
    }

    /**
     * Removes a child from the layout.
     *
     * When the child is removed, the layout would be re-renderd.
     *
     * @param child The child view to be removed
     * @return True if the child was removed
     */
    open fun removeChild(child: View): Boolean {
        val removed = childViews.remove(child)
        if (removed) {
            child.parent = null
        }
        return removed
    }
}