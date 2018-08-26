package core.loaders.keys

import core.loaders.viewTree.IllegalViewTreeException
import utils.extensions.nameAsLowerUnderscore
import utils.validators.conditions.StringConditions
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

abstract class ViewKeys: HasKeys {

    override var keys: MutableMap<String, Any?> by Delegates.vetoable(mutableMapOf()) { _, _, newVal ->
        newVal.keys.forEach { key ->
            if (!StringConditions.LOWER_CAMEL.isValid(key)) {
                throw IllegalViewTreeException("Key $key is not in a lowerCamel format")
            }
        }
        //checkKeyConflicts(newVal.keys)
        true
    }

    /*
    open val conflictingKeys: Set<Set<KProperty<*>>> = setOf()

    private val conflictingStrKeys: Set<Set<String>>
        get() = conflictingKeys.map { conflictGrp ->
            conflictGrp.map { kprop -> kprop.nameAsLowerUnderscore() }.toSet() }.toSet()

    private fun checkKeyConflicts(keys: Set<String>) {
        keys.forEach { k1 ->
            val conflictGrps = locateKeyConflictGroups(k1)
            conflictGrps.forEach { grp ->
                keys.forEach { k2 ->
                    if (k1 != k2 && k2 in grp) {
                        IllegalViewTreeException("Keys $k1 and $k2 are incompatible")
                    }
                }
            }
        }
    }

    private fun locateKeyConflictGroups(key: String) = conflictingStrKeys.filter { set -> key in set }
    */
}