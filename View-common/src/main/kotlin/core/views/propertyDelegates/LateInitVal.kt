package core.views.propertyDelegates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LateInitVal<T>: ReadWriteProperty<Any, T> {

    private var value: T? = null
    private var isSet = false

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("Property is not initialized")
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        if (isSet) {
            throw IllegalStateException("Property is already set")
        }
        this.value = value
        isSet = true
    }
}