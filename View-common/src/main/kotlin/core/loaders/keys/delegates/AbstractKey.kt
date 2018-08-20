package core.loaders.keys.delegates

import core.loaders.keys.HasKeys
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class AbstractKey<T: Any?>: ReadWriteProperty<HasKeys, T> {

    protected var mapIndependentValue: T? = null

    override fun setValue(thisRef: HasKeys, property: KProperty<*>, value: T) {
        mapIndependentValue = value
    }
}