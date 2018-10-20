package utils.mapBased.keys.delegates.required

import utils.mapBased.keys.HasKeys
import utils.mapBased.keys.MapInterface
import utils.mapBased.keys.delegates.AbstractRWKey
import kotlin.reflect.KProperty

open class RequiredRWKey<T>(private val fromString: (String) -> T): AbstractRWKey<T>() {

    override val getterStrategy = listOf(
            MapInterface.Strategy.NON_NULL,
            MapInterface.Strategy.STRING_BASED,
            MapInterface.Strategy.CAST
    )

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T {
        return mapInterface(thisRef.keys).getValue(property.name)!!
    }

    override fun mapInterface(keys: MutableMap<String, Any?>): MapInterface<T> {
        val minterface = super.mapInterface(keys)
        minterface.fromString = fromString
        return minterface
    }
}