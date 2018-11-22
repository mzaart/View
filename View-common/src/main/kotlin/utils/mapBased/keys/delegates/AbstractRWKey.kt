package utils.mapBased.keys.delegates

import utils.mapBased.keys.HasKeys
import utils.mapBased.keys.MapInterface
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A delegate for mutable properties that are based on maps.
 */
abstract class AbstractRWKey<T>: ReadWriteProperty<HasKeys, T> {

    /**
     * Defines the value retrieval stategy of the property
     */
    protected abstract val getterStrategy: List<MapInterface.Strategy>

    override fun setValue(thisRef: HasKeys, property: KProperty<*>, value: T) {
        mapInterface(thisRef.keys).setValue(property.name, value)
    }

    /**
     * Returns the [MapInterface] used to communicate with the map.
     *
     * @param keys The map that contains the values
     * @return The [MapInterface] used to communicate with the map.
     */
    protected open fun mapInterface(keys: MutableMap<String, Any?>) = MapInterface<T>(keys, getterStrategy)
}
