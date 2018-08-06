package utils.validators

class Validator<T: Any>(
        private val conditions: List<Condition<T>>,
        private val strategy: Strategy
) {

    enum class Strategy {
        UNANIMOUS, // equivalent to AND
        AFFIRMATIVE, // equivalent to OR
        CONSENSUS // equivalent to democracy
    }

    constructor(vararg conditions: Condition<T>): this(listOf(conditions) as List<Condition<T>>, Strategy.UNANIMOUS)

    fun validate(value: T) {
        var countSuccess = 0
        var countFailed = 0
        for (condition in conditions) {
            if (condition.validate(value)) countSuccess++ else countFailed++
        }

        val isValid = when (strategy) {
            Strategy.UNANIMOUS -> countFailed == 0
            Strategy.AFFIRMATIVE -> countSuccess > 0
            Strategy.CONSENSUS -> countSuccess > countFailed
        } || conditions.isEmpty()

        if (isValid) {
            throw ValidationException(value, conditions as List<Condition<Any>>, strategy)
        }
    }
}