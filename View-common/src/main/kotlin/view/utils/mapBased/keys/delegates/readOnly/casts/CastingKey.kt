package view.utils.mapBased.keys.delegates.readOnly.casts

import view.utils.mapBased.keys.HasKeys
import view.utils.mapBased.keys.MapInterface
import view.utils.mapBased.keys.delegates.AbstractRWKey
import kotlin.reflect.KProperty

/**
 * Has the same behaviour as delegation by a map.
 */
class CastingKey<T>: AbstractRWKey<T?>() {

    override val getterStrategy = listOf(MapInterface.Strategy.CAST)

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T? {
        return mapInterface(thisRef.keys).getValue(property.name)
    }
}