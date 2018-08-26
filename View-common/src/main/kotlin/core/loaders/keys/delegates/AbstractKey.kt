package core.loaders.keys.delegates

import core.loaders.keys.HasKeys
import core.loaders.viewTree.IllegalViewTreeException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class AbstractKey<T: Any?>(
        private vararg val conflictingKeys: String
): ReadWriteProperty<HasKeys, T> {

    override fun setValue(thisRef: HasKeys, property: KProperty<*>, value: T) {
        conflictingKeys.forEach { k ->
            if (thisRef.keys.keys.contains(k)) {
                IllegalViewTreeException("Conflicting key $k")
            }
        }
        thisRef.keys[property.name] = value
    }
}