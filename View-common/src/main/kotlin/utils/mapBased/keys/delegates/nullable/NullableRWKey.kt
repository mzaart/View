package utils.mapBased.keys.delegates.nullable

import utils.mapBased.keys.HasKeys
import utils.mapBased.keys.MapInterface
import utils.mapBased.keys.delegates.AbstractRWKey
import kotlin.reflect.KProperty

open class NullableRWKey<T>(private val fromString: (String) -> T): AbstractRWKey<T?>() {

    override val getterStrategy = listOf(
            MapInterface.Strategy.CAST,
            MapInterface.Strategy.STRING_BASED
    )

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T? {
        return mapInterface(thisRef.keys).getValue(property.name)
    }

    override fun mapInterface(keys: MutableMap<String, Any?>): MapInterface<T?> {
        val minterface = super.mapInterface(keys)
        minterface.fromString = fromString
        return minterface
    }
}