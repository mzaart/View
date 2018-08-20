package core.loaders.keys.delegates.required

import core.loaders.viewTree.IllegalViewTreeException
import core.loaders.keys.HasKeys
import core.loaders.keys.delegates.AbstractKey
import utils.extensions.nameAsLowerUnderscore
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class RequiredKey<T>(private val getKeyValue: (String) -> T): AbstractKey<T>() {

    override fun getValue(thisRef: HasKeys, property: KProperty<*>): T {
        if (mapIndependentValue != null) {
            return mapIndependentValue as T
        }
        val key = property.nameAsLowerUnderscore()
        if (key !in thisRef.keys.keys) {
            throw IllegalViewTreeException(setOf(key))
        }
        return getKeyValue(thisRef.keys[key]!!)
    }
}