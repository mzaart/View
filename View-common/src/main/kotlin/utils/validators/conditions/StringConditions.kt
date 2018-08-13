package utils.validators.conditions

typealias SC = StringConditions

enum class StringConditions: Condition<String> {

    EMPTY {
        override fun isValid(value: String) = value.isEmpty()
    },

    WHITE_SPACE {
        override fun isValid(value: String) = value.isBlank()
    },

    NON_EMPTY {
        override fun isValid(value: String) = value.isNotEmpty()
    },

    PRESENT {
        override fun isValid(value: String) = value.isNotBlank()
    },

    UPPER_UNDERSCORE {
        override fun isValid(value: String) = """^([A-Z]|\d)+(_([A-Z]|\d)+)*${'$'}""".toRegex().matches(value)
    },

    LOWER_UNDERSCORE {
        override fun isValid(value: String) = """^([a-z]|\d)+(_([a-z]|\d)+)*${'$'}""".toRegex().matches(value)
    },

    UPPER_CAMEL {
        override fun isValid(value: String) = """^[A-Z][a-zA-Z0-9]*${'$'}""".toRegex().matches(value)
    },

    LOWER_CAMEL {
        override fun isValid(value: String) = """^[a-z][a-zA-Z0-9]*${'$'}""".toRegex().matches(value)
    }
}