package utils.validators.conditions

typealias IC = IntegerConditions

enum class IntegerConditions: Condition<Int> {

    ZERO {
        override fun isValid(value: Int) = value == 0
    },

    POSITIVE {
        override fun isValid(value: Int) = value > 0
    },

    NEGATIVE {
        override fun isValid(value: Int) = value < 0
    },

    NON_POSITIVE {
        override fun isValid(value: Int) = value <= 0
    },

    NON_NEGATIVE {
        override fun isValid(value: Int) = value >= 0
    }
}