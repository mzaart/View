package view.utils.mapBased.keys.delegates.nullable

import view.utils.mapBased.keys.HasKeys
import view.utils.mapBased.keys.MapInterface
import view.utils.mapBased.keys.delegates.AbstractRWKey
import kotlin.reflect.KProperty

/**
 * A delegate for mutable nullable properties that are based on maps.
 *
 * The retrieval strategy is defined as follows:
 *
 * [MapInterface.Strategy.STRING_BASED], [MapInterface.Strategy.CAST]
 */
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