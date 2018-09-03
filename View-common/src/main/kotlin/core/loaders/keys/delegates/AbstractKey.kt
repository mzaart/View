package core.loaders.keys.delegates

import core.loaders.keys.HasKeys
import core.loaders.viewTree.IllegalViewTreeException
import utils.validators.conditions.StringConditions
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class AbstractKey<T: Any?>: ReadWriteProperty<HasKeys, T> {

    override fun setValue(thisRef: HasKeys, property: KProperty<*>, value: T) {
        val keyName = property.name
        if (!StringConditions.LOWER_CAMEL.isValid(keyName)) {
            throw IllegalViewTreeException("Illegal key name $keyName. Key names should be in camelCase")
        }
        thisRef.keys[keyName] = value
    }

    protected fun <T> getAs(keyName: String, value: Any?): T = try {
        value as T
    } catch (e: ClassCastException) {
        throw IllegalViewTreeException("Invalid value for key ${keyName}")
    }
}