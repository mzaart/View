package core.loaders.keys

import core.loaders.viewTree.IllegalViewTreeException
import utils.validators.conditions.StringConditions
import kotlin.properties.Delegates

abstract class ViewKeys: HasKeys {

    override var keys: MutableMap<String, Any?> by Delegates.vetoable(mutableMapOf()) { _, _, newVal ->
        newVal.keys.forEach { key ->
            if (!StringConditions.LOWER_CAMEL.isValid(key)) {
                throw IllegalViewTreeException("Key $key is not in lowerCamel format")
            }
        }
        true
    }
}