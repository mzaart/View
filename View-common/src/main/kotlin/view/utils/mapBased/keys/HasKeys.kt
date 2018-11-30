package view.utils.mapBased.keys

/**
 * A base class for classes whose properties are based on a map.
 */
open class HasKeys() {

    /**
     * The map that contains the property values
     */
    open lateinit var keys: MutableMap<String, Any?>

    /**
     * Initializes the class using a mutable map
     *
     * @param keys The map that contains the property values
     */
    constructor(keys: MutableMap<String, Any?>) : this() {
        this.keys = keys
    }
}