package core.views.propertyDelegates

import core.views.View
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LateInitVal<T>: ReadWriteProperty<View, T> {

    private var value: T? = null
    private var isSet = false

    override fun getValue(thisRef: View, property: KProperty<*>): T {
        return value ?: throw RuntimeException("Property is not initialized")
    }

    override fun setValue(thisRef: View, property: KProperty<*>, value: T) {
        if (isSet) {
            throw RuntimeException("Property is already set")
        }
        this.value = value
        isSet = true
    }
}