package core.loaders.keys.delegates.required

import core.loaders.viewTree.IllegalViewTreeException
import core.loaders.keys.HasKeys
import core.loaders.keys.delegates.AbstractKey
import kotlin.reflect.KProperty

open class RequiredKey<T>(private val getKeyValue: (String) -> T): AbstractKey<T>() {

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T {
        val keyName = property.name
        val keyVal = thisRef.keys[keyName]
        return when (keyVal) {
            null -> throw IllegalViewTreeException(setOf(keyName))
            is String -> getKeyValue(keyVal)
            else -> getAs(keyName, keyVal)
        }
    }
}