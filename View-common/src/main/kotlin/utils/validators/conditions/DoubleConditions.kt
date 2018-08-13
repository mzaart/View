package utils.validators.conditions

typealias DC = DoubleConditions

enum class DoubleConditions: Condition<Double> {

    ZERO {
        override fun isValid(value: Double) = value == 0.0
    },

    POSITIVE {
        override fun isValid(value: Double) = value > 0.0
    },

    NEGATIVE {
        override fun isValid(value: Double) = value < 0.0
    },

    NON_POSITIVE {
        override fun isValid(value: Double) = value <= 0.0
    },

    NON_NEGATIVE {
        override fun isValid(value: Double) = value >= 0.0
    }
}