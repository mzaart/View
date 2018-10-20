package utils.mapBased.keys.delegates

import utils.mapBased.keys.HasKeys
import utils.mapBased.keys.MapInterface
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class AbstractRWKey<T>: ReadWriteProperty<HasKeys, T> {

    protected abstract val getterStrategy: List<MapInterface.Strategy>

    override fun setValue(thisRef: HasKeys, property: KProperty<*>, value: T) {
        mapInterface(thisRef.keys).setValue(property.name, value)
    }

    protected open fun mapInterface(keys: MutableMap<String, Any?>) = MapInterface<T>(keys, getterStrategy)

}
