package view.core.views.propertyDelegates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Allows an immutable property to be initialized later in the class' lifetime.
 *
 * The purpose of this delegate is to overcome the limitations of the builtin 'lateinit' modifier which cannot be
 * applied to properties of primitive types and immutable properties.
 */
open class LateInitVal<T>: ReadWriteProperty<Any, T> {

    private var value: T? = null
    private var isSet = false

    /**
     * Returns the property value.
     *
     * @throws IllegalStateException If the property isn't initialized.
     */
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("Property is not initialized")
    }

    /**
     * Sets the property value.
     *
     * @throws IllegalStateException If the property is already initialized.
     */
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        if (isSet) {
            throw IllegalStateException("Property is already set")
        }
        this.value = value
        isSet = true
    }
}