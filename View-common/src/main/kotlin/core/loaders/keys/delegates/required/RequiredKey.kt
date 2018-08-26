package core.loaders.keys.delegates.required

import core.loaders.viewTree.IllegalViewTreeException
import core.loaders.keys.HasKeys
import core.loaders.keys.delegates.AbstractKey
import kotlin.reflect.KProperty

open class RequiredKey<T>(
        private val getKeyValue: (String) -> T,
        vararg conflictingKeys: String
): AbstractKey<T>(*conflictingKeys) {

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T {
        val propName = property.name
        val keyVal = thisRef.keys[propName]
        return when (keyVal) {
            null -> throw IllegalViewTreeException(setOf(propName))
            is String -> getKeyValue(keyVal)
            else -> keyVal as T
        }
    }
}