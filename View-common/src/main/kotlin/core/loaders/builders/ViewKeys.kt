package core.loaders.builders

import core.loaders.viewTree.IllegalViewTreeException
import utils.mapBased.keys.HasKeys
import utils.validators.conditions.StringConditions
import kotlin.properties.Delegates

/**
 * Base class for classes containing view key-value pairs.
 *
 * This class validates the keys before being assigned.
 */
abstract class ViewKeys: HasKeys() {

    override var keys: MutableMap<String, Any?> by Delegates.vetoable(mutableMapOf()) { _, _, newVal ->
        newVal.keys.forEach { key ->
            if (!StringConditions.LOWER_CAMEL.isValid(key)) {
                throw IllegalViewTreeException("NullableRWKey $key is not in lowerCamel format")
            }
        }
        true
    }
}