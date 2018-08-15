package utils.validators

import utils.validators.conditions.Condition

class Validator<T: Any>(
        private val strategy: Strategy,
        private vararg val conditions: Condition<T>
) {

    enum class Strategy {
        UNANIMOUS, // equivalent to AND
        AFFIRMATIVE, // equivalent to OR
        CONSENSUS // equivalent to democracy
    }

    constructor(vararg conditions: Condition<T>): this(Strategy.UNANIMOUS, *conditions)

    fun validate(value: T) {
        var countSuccess = 0
        var countFailed = 0
        conditions.forEach { c -> if (c.isValid(value)) countSuccess++ else countFailed++ }

        val isValid = when (strategy) {
            Strategy.UNANIMOUS -> countFailed == 0
            Strategy.AFFIRMATIVE -> countSuccess > 0
            Strategy.CONSENSUS -> countSuccess > countFailed
        } || conditions.isEmpty()

        if (!isValid) {
            throw ValidationException(value, conditions as List<Condition<Any>>, strategy)
        }
    }
}