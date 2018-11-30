package view.core.views.propertyDelegates

import view.core.views.View
import view.utils.validators.Validator
import kotlin.reflect.KProperty

/**
 * Represents a nullable and mutable view property.
 *
 * @property value The initial value of the property.
 * @property validator Validates the property value.
 * @constructor Creates the delegate with an initial value
 */
class ViewProperty<T: Any>(
        value: T,
        private val validator: Validator<T>? = null
): AbstractViewProperty<T>(value) {

    /**
     * Sets the property value.
     *
     * This method validates the value first before setting the value. Once the value is
     * set, the [ViewTreeRenderer][view.core.renderers.ViewTreeRenderer] is notified about the change in value.
     */
    override operator fun setValue(thisRef: View, property: KProperty<*>, value: T) {
        validator?.validate(value)
        this.value = value
        renderer.invalidate(thisRef)
    }
}