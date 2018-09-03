package utils.validators.conditions

typealias LC = LongConditions

enum class LongConditions: Condition<Long> {

    RGB {
        override fun isValid(value: Long) = value in 0x000000..0xFFFFFF
    },

    RGBA {
        override fun isValid(value: Long) = value in 0x00000000..0xFFFFFFFF
    },

    COLOR {
        override fun isValid(value: Long) = RGB.isValid(value) || RGBA.isValid(value)
    }
}