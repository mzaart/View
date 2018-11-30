package view.core.views.propertyDelegates

import view.core.renderers.ViewTreeRenderer
import view.core.views.View
import view.di.inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * This is the base class for view mutable property delegates.
 *
 * When a value is set, the subclass delegate should notify the [ViewTreeRenderer] of the change in the view's
 * property.
 *
 * @property value The initial value of the property.
 * @constructor Creates the delegate with an initial value
 */
abstract class AbstractViewProperty<T>(protected var value: T): ReadWriteProperty<View, T> {

    /**
     * The [ViewTreeRenderer] that is responsible for rendering the view that this delegate is part of.
     */
    protected val renderer by inject<ViewTreeRenderer>()

    /**
     * Returns the property value.
     */
    override fun getValue(thisRef: View, property: KProperty<*>) = value
}