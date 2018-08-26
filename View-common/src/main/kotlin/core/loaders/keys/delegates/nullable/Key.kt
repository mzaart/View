package core.loaders.keys.delegates.nullable

import core.loaders.keys.HasKeys
import core.loaders.keys.delegates.AbstractKey
import utils.extensions.lowerCamelToLowerUnderscore
import kotlin.reflect.KProperty

open class Key<T>(
        private val getKeyValue: (String) -> T,
        vararg conflicts: String
): AbstractKey<T?>(*conflicts) {

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T? {
        val keyVal = thisRef.keys[property.name]
        return when (keyVal) {
            null -> null
            is String -> getKeyValue(keyVal)
            else -> keyVal as T
        }
    }
}