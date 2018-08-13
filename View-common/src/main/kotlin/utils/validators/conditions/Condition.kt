package utils.validators.conditions

interface Condition<T> {

    fun isValid(value: T): Boolean
}