package core.loaders.keys.delegates.nullable

import core.loaders.keys.HasKeys
import core.loaders.keys.delegates.AbstractKey
import utils.extensions.lowerCamelToLowerUnderscore
import kotlin.reflect.KProperty

open class Key<T>(private val getKeyValue: (String) -> T): AbstractKey<T?>() {

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T? {
        if (mapIndependentValue != null) {
            return mapIndependentValue as T
        }
        val propName = property.name.lowerCamelToLowerUnderscore()
        val keyVal = thisRef.keys[propName]
        return if (keyVal == null) null else getKeyValue(keyVal)
    }
}