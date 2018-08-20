package core.loaders.keys

import core.loaders.viewTree.IllegalViewTreeException
import utils.extensions.nameAsLowerUnderscore
import utils.validators.conditions.StringConditions
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

abstract class ViewKeys: HasKeys {

    override var keys: MutableMap<String, String> by Delegates.vetoable(mutableMapOf()) { _, _, newVal ->
        newVal.keys.forEach { key ->
            if (!StringConditions.LOWER_UNDERSCORE.isValid(key)) {
                throw IllegalViewTreeException("Key $key is not in a lower_underscore format")
            }
        }
        checkKeyConflicts(newVal.keys)
        true
    }

    open val conflictingKeys: Set<Set<KProperty<*>>> = setOf()

    private val conflictingStrKeys: Set<Set<String>>
        get() = conflictingKeys.map { conflictGrp ->
            conflictGrp.map { kprop -> kprop.nameAsLowerUnderscore() }.toSet() }.toSet()

    private fun checkKeyConflicts(keys: Set<String>) {
        keys.forEach { k1 ->
            val grp = locateKeyConflictGroup(k1)
            keys.forEach { k2 ->
                if (k1 != k2 && k2 in grp) {
                    IllegalViewTreeException("Keys $k1 and $k2 are incompatible")
                }
            }
        }
    }

    private fun locateKeyConflictGroup(key: String) = conflictingStrKeys.first { set -> key in set }
}