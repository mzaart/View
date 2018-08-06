package utils.validators

class ValidationException(message: String): RuntimeException(message) {
    constructor(value: Any, conditions: List<Condition<Any>>, strategy: Validator.Strategy):
            this("Validation failed with value $value, conditions $conditions and strategy $strategy.")
}