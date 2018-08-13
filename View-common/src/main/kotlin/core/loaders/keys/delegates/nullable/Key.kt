package core.loaders.keys.delegates.nullable

import core.loaders.keys.HasKeys
import utils.extensions.lowerCamelToLowerUnderscore
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

open class Key<T>(private val getKeyValue: (String) -> T): ReadOnlyProperty<HasKeys, T?> {

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T? {
        val propName = property.name.lowerCamelToLowerUnderscore()
        val keyVal = thisRef.keys[propName]
        return if (keyVal == null) null else getKeyValue(keyVal)
    }
}