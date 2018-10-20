package utils.mapBased.keys

import core.loaders.viewTree.IllegalViewTreeException
import utils.validators.conditions.StringConditions

class MapInterface<T>(
        private val keys: MutableMap<String, Any?>,
        private val getterStrategy: List<Strategy>,
        private val setterStrategy: Strategy = Strategy.CAST
) {

    enum class Strategy {
        STRING_BASED,
        CAST,
        NON_NULL
    }

    var fromString: ((String) -> T)? = null
    var deserialize: ((T) -> String)? = null

    fun getValue(key: String): T? {
        val value = keys[key]

        if (value == null) {
            if (Strategy.NON_NULL in getterStrategy) {
                throw IllegalViewTreeException(setOf(key))
            }
            return null
        }


        if (value is String) {
            if (fromString == null) {
                /*
                Can't check if the value type matches the required at runtime on JS.
                If the value is String and fromString function isn't specified, I'll assume
                the user wants a String but this might not be the case sometimes.
                 */
                return value as T
            } else {
                if (Strategy.STRING_BASED !in getterStrategy) {
                    throw IllegalStateException()
                } else {
                    return fromString!!(value)
                }
            }
        } else {
            if (Strategy.CAST in getterStrategy) {
                return value as T
            }
        }

        throw IllegalStateException("Invalid strategy")
    }

    fun setValue(key: String, value: T) {
        if (!StringConditions.LOWER_CAMEL.isValid(key)) {
            throw IllegalViewTreeException("Illegal key name $key. NullableRWKey names should be in camelCase")
        }

        if (setterStrategy == Strategy.CAST) {
            keys[key] = value
        } else if (setterStrategy == Strategy.STRING_BASED) {
            keys[key] = deserialize!!(value)
        } else {
            throw IllegalStateException("Invalid Strategy")
        }
    }
}