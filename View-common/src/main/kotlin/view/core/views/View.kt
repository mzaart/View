package view.core.views

import view.core.views.layouts.Layout
import view.core.views.propertyDelegates.*
import view.utils.mapBased.keys.HasKeys
import view.utils.validators.Validator
import view.utils.validators.conditions.DC
import view.utils.validators.conditions.LC

/**
 * This class represents a user interface component and acts as a base class for all other Views. It
 * contains some basic properties common to all Views.
 *
 * A View can be created using code, specified using the View DSL or [loaded from a
 * serialized format][link]. All Views to be rendered are arranged in a tree-like hierarchy where each
 * non-leaf node is a subclass of [Layout]. Once a View Tree is created, it can be rendered using a
 * platform specific [ViewTreeRenderer][view.core.renderers.ViewTreeRenderer].
 *
 * Most view properties are implemented using custom property delegates that take care of validation
 * and updating the [ViewTreeRenderer][view.core.renderers.ViewTreeRenderer].
 * @see [view.core.views.propertyDelegates]
 * @see [view.utils.validators]
 *
 */
abstract class View {

    /**
     * This enum describes the view's visibility status.
     */
    enum class Visibility {
        /**
         * The view is visible.
         */
        VISIBLE,

        /**
         * The view is not visible but its position and dimensions are still present on the UI.
         */
        INVISIBLE,

        /**
         * The UI will be displayed as if this the view doesn't exist.
         */
        GONE
    }

    /**
     * A name associated with a UI element. Usually it is the class name of the view sub-class.
     */
    val name: String
        get() = this::class.simpleName!!

    /**
     * A unique integer value that identifies a view instance.
     */
    var id: Int by LateInitVal()

    /**
     * The [Theme] to be applied to this view instance.
     */
    var theme: String? by LateInitVal()

    /**
     * Describes the view's height. Values between [0, 1] represent relative dimensions while
     * values greater than 1 represent absolute dimensions.
     *
     * @see Dimension.Type
     */
    var width: Double by ViewProperty(Dimension.value(Dimension.Type.WRAP_CONTENT))

    /**
     * Describes the view's width. Values between [0, 1] represent relative dimensions while
     * values greater than 1 represent absolute dimensions.
     *
     * @see Dimension.Type
     */
    var height: Double by ViewProperty(Dimension.value(Dimension.Type.WRAP_CONTENT))

    /**
     * A reference to the view's parent. Semantically, if this value is null then that implies
     * the view is either unattached to a View Tree or it is the root of a View Tree.
     */
    var parent: Layout? = null

    /**
     * Indicates whether a view is disabled or not. Although the behaviour of a disabled view varies
     * from one UI element to another; common behaviour exists for all UI elements such as being
     * unresponsive to click events.
     */
    var disabled: Boolean by ViewProperty(false)

    /**
     * Indicates the visibility status of the view.
     *
     * @see Visibility
     */
    var visibility: Visibility by ViewProperty(Visibility.VISIBLE)

    /**
     * Specifies the value of the top margin of the view.
     */
    var marginTop: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))

    /**
     * Specifies the value of the bottom margin of the view.
     */
    var marginBottom: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))

    /**
     * Specifies the values of the margin ath the beginning of the view.
     */
    var marginStart: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))

    /**
     * Specifies the values of the margin at the end of the view.
     */
    var marginEnd: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))

    /**
     * Specifies the value of the top padding of the view.
     */
    var paddingTop: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))

    /**
     * Specifies the value of the bottom padding of the view.
     */
    var paddingBottom: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))

    /**
     * Specifies the values of the padding a the beginning of the view.
     */
    var paddingStart: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))

    /**
     * Specifies the values of the padding at the end of the view.
     */
    var paddingEnd: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))

    /**
     * Specifies the background color of a value. This value is to be interpreted as a hexadecimal
     * RGBA representation of the color.
     */
    var backgroundColor: Long? by NullableViewProperty(Validator(LC.COLOR))

    /**
     * A listener to be invoked whenever a the UI element corresponding to this view is clicked.
     */
    var onClickListener by NullableViewProperty<(View) -> Unit>()

    /**
     * A listener to be invoked whenever a the UI element corresponding to this view is clicked and held.
     */
    var onLongClickListener by NullableViewProperty<(View) -> Unit>()

    /**
     * A listener to b invoked whenever the UI element corresponding to this view is resized.
     */
    var onResize by NullableViewProperty<(View) -> Unit>()

    /**
     * Key-Value pairs to be interpreted by a Web specific
     * [ViewTreeRenderer][view.core.renderers.ViewTreeRenderer].
     */
    var webExtras: HasKeys? = null

    /**
     * Key-Value pairs to be interpreted by an Android specific
     * [ViewTreeRenderer][view.core.renderers.ViewTreeRenderer].
     */
    var androidExtras: HasKeys? = null

    /**
     * Key-Value pairs to be interpreted by an iOs specific
     * [ViewTreeRenderer][view.core.renderers.ViewTreeRenderer].
     */
    var iosExtras: HasKeys? = null
}