package utils.mapBased.keys

open class HasKeys() {

    open lateinit var keys: MutableMap<String, Any?>

    constructor(keys: MutableMap<String, Any?>) : this() {
        this.keys = keys
    }
    constructor(keys: Map<String, Any?>): this(keys.toMutableMap())
    constructor(keys: Map<String, Any>): this(keys as Map<String, Any?>)
}