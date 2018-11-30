package view.utils.validators

import view.utils.validators.conditions.Condition

/**
 * A utility class for validation.
 *
 * This class allows for easy construction of multiple validation criteria. It uses a combination of
 * [validation strategies][Strategy] and [conditions][Condition]
 *
 * @constructor Initializes the object with a validation strategy and conditions.
 */
class Validator<T: Any>(
        private val strategy: Strategy,
        private vararg val conditions: Condition<T>
) {

    /**
     * Represents a validation strategy,
     */
    enum class Strategy {

        /**
         * All the conditions should be met.
         */
        UNANIMOUS,

        /**
         * At least one condition should be met.
         */
        AFFIRMATIVE,

        /**
         * The number of met conditions should be greater than the of unmet conditions.
         */
        CONSENSUS
    }

    /**
     * Initializes the object with given conditions and sets the validation strategy to [Strategy.UNANIMOUS]
     */
    constructor(vararg conditions: Condition<T>): this(Strategy.UNANIMOUS, *conditions)

    /**
     * Validates the passed values.
     *
     * @throws ValidationException If the passed value doesn't pass the validation strategy
     */
    fun validate(value: T) {
        var countSuccess = 0
        var countFailed = 0
        conditions.forEach { c -> if (c.isValid(value)) countSuccess++ else countFailed++ }

        val isValid = when (strategy) {
            Strategy.UNANIMOUS -> countFailed == 0
            Strategy.AFFIRMATIVE -> countSuccess > 0
            Strategy.CONSENSUS -> countSuccess >= countFailed
        } || conditions.isEmpty()

        if (!isValid) {
            throw ValidationException(value, conditions.map { it.toString() }, strategy)
        }
    }
}