package utils.validators

class ValidationException(message: String): RuntimeException(message) {
    constructor(value: Any, conditions: List<String>, strategy: Validator.Strategy):
            this("Validation failed with value $value, conditions $conditions and strategy $strategy.")
}