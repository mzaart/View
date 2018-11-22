package utils.validators.conditions

/**
 * Represents a condition that the a value should meet to be valid.
 */
interface Condition<T> {

    /**
     * Checks if the passed value is valid.
     *
     * @param value The value to be checked
     * @return True if the value is valud, false otherwise
     */
    fun isValid(value: T): Boolean
}