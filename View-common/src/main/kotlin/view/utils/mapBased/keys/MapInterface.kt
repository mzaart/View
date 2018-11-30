package view.utils.mapBased.keys

import view.core.loaders.viewTree.IllegalViewTreeException
import view.utils.validators.conditions.StringConditions

/**
 * Acts as layer between the property delegate and the map.
 *
 * This class allows to define strategies for setting and retrieving values from the map.
 *
 * @see MapInterface.Strategy
 *
 * @constructor Initializes the object with the map and getter/setter strategies.
 * @param keys The map that holds the values
 * @param getterStrategy Defines how values should be retrieved from the map
 * @param setterStrategy Defines how values should be set in the map
 */
class MapInterface<T>(
        private val keys: MutableMap<String, Any?>,
        private val getterStrategy: List<Strategy>,
        private val setterStrategy: Strategy = Strategy.CAST
) {

    /**
     * Represents a strategy for setting and retrieving values from the map.
     */
    enum class Strategy {

        /**
         * Specifies that the object in the map is stored in a string.
         *
         * When retrieving the object, the property [fromString], which returns the object from the string, should be set.
         * When setting the object the property [serialize], which serializes the object, should be set.
         */
        STRING_BASED,

        /**
         * The object instance exists in the map and should be cast to back to its original type when retrieved.
         */
        CAST,

        /**
         * The value of the object in the map cannot be null.
         */
        NON_NULL
    }

    /**
     * A function that returns an object instance from a given string.
     */
    var fromString: ((String) -> T)? = null

    /**
     * A function that encodes the object to a string.
     */
    var serialize: ((T) -> String)? = null

    /**
     * Retrieves the property value with given name from the map.
     *
     * The strategies present in [getterStrategy] are applied in the following order: [Strategy.NON_NULL],
     * [Strategy.STRING_BASED], [Strategy.CAST].
     *
     * @param key The property's name
     * @return The property's value
     *
     * @throws IllegalViewTreeException If [getterStrategy] contains [Strategy.NON_NULL] and the property value
     * is not present in the map.
     * @throws IllegalStateException If [getterStrategy] contains [Strategy.STRING_BASED] and [fromString] is not set
     * @throws ClassCastException If [getterStrategy] contains [Strategy.CAST] and the value if of different type.
     */
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

    /**
     * Sets the property value.
     *
     * @param key The property's name
     * @param value The property's value
     *
     * @throws IllegalViewTreeException If the property name is not in camelCase
     * @throws NullPointerException If [setterStrategy] is [Strategy.STRING_BASED] and [serialize] is null
     */
    fun setValue(key: String, value: T) {
        if (!StringConditions.LOWER_CAMEL.isValid(key)) {
            throw IllegalViewTreeException("Illegal key name $key. Key names should be in camelCase")
        }
        if (setterStrategy == Strategy.CAST) {
            keys[key] = value
        } else if (setterStrategy == Strategy.STRING_BASED) {
            keys[key] = serialize!!(value)
        } else {
            throw IllegalStateException("This won't be reached")
        }
    }
}