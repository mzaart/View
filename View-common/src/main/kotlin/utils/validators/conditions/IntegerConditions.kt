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
    },

    RGB {
        override fun isValid(value: Int) = value in 0x000000..0xFFFFFF
    },

    RGBA {
        override fun isValid(value: Int) = value in 0x00000000..0xFFFFFFFF
    },

    COLOR {
        override fun isValid(value: Int) = RGB.isValid(value) || RGBA.isValid(value)
    }
}