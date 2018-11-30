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
open class NullableViewProperty<T: Any>(
        value: T? = null,
        private val validator: Validator<T>? = null
): AbstractViewProperty<T?>(value) {

    /**
     * Initialized the property with a null value.
     *
     * @param validator The validator that validates the property value.
     */
    constructor(validator: Validator<T>?): this(null, validator)

    /**
     * Sets the property value.
     *
     * This method validates the value first before setting the value. Once the value is
     * set, the [ViewTreeRenderer][view.core.renderers.ViewTreeRenderer] is notified about the change in value.
     */
    override fun setValue(thisRef: View, property: KProperty<*>, value: T?) {
        if (value != null) {
            validator?.validate(value)
        }
        this.value = value
        renderer.invalidate(thisRef)
    }
}