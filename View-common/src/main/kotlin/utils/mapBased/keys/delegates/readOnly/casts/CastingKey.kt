package utils.mapBased.keys.delegates.readOnly.casts

import utils.mapBased.keys.HasKeys
import utils.mapBased.keys.MapInterface
import utils.mapBased.keys.delegates.AbstractRWKey
import kotlin.reflect.KProperty

class CastingKey<T>: AbstractRWKey<T?>() {

    override val getterStrategy = listOf(MapInterface.Strategy.CAST)

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T? {
        return mapInterface(thisRef.keys).getValue(property.name)
    }
}