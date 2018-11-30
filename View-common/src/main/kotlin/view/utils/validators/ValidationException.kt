package view.utils.validators

/**
 * An exception that is thrown whenever a value doesn't pass a validation.
 *
 * @constructor Initializes the exception with a message
 */
class ValidationException(message: String): RuntimeException(message) {

    /**
     * Initializes the exception with a message containing information on the failed validation.
     */
    constructor(value: Any, conditions: List<String>, strategy: Validator.Strategy):
            this("Validation failed with value $value, conditions $conditions and strategy $strategy.")
}