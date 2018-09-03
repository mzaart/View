package core.loaders.keys.delegates.nullable

import core.loaders.keys.HasKeys
import core.loaders.keys.delegates.AbstractKey
import core.loaders.viewTree.IllegalViewTreeException
import kotlin.reflect.KProperty

open class Key<T>(private val getKeyValue: (String) -> T): AbstractKey<T?>() {

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T? {
        val keyVal = thisRef.keys[property.name]
        return when (keyVal) {
            null -> null
            is String -> getKeyValue(keyVal)
            else -> getAs(property.name, keyVal)
        }
    }
}