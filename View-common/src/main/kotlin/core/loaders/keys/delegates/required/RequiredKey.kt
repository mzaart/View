package core.loaders.keys.delegates.required

import core.loaders.IllegalViewTreeException
import core.loaders.keys.HasKeys
import utils.extensions.nameAsLowerUnderscore
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

open class RequiredKey<T>(private val getKeyValue: (String) -> T): ReadOnlyProperty<HasKeys, T> {

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T {
        val key = property.nameAsLowerUnderscore()
        if (key !in thisRef.keys.keys) {
            throw IllegalViewTreeException(setOf(key))
        }
        return getKeyValue(thisRef.keys[key]!!)
    }
}