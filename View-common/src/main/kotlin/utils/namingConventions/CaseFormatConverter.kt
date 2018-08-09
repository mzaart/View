package utils.namingConventions

class CaseFormatConverter {

    enum class Format {
        LOWER_CAMEL,
        UPPER_CAMEL,
        LOWER_UNDERSCORE,
        UPPER_UNDERSCORE
    }

    companion object {

        fun convert(from: Format, to: Format, str: String): String {
            return when (Pair(from, to)) {
                Pair(Format.UPPER_UNDERSCORE, Format.LOWER_UNDERSCORE)
                    -> convertUpperUnderscoreToLowerUnderscore(str)
                Pair(Format.LOWER_UNDERSCORE, Format.UPPER_UNDERSCORE)
                    -> convertLowerUnderscoreToUpperUnderscore(str)
                else -> throw NotImplementedError("Conversion from $from to $to is not supported yet")
            }
        }

        private fun convertUpperUnderscoreToLowerUnderscore(str: String): String {
            isFormatValid(str, Format.UPPER_UNDERSCORE)
            return str.toLowerCase()
        }

        private fun convertLowerUnderscoreToUpperUnderscore(str: String): String {
            isFormatValid(str, Format.LOWER_UNDERSCORE)
           return str.toUpperCase()
        }

        private fun isFormatValid(str: String, format: Format) {
            val isValid = when (format) {
                CaseFormatConverter.Format.LOWER_CAMEL -> """^[a-z][a-zA-Z0-9]*${'$'}"""
                CaseFormatConverter.Format.UPPER_CAMEL -> """^[A-Z][a-zA-Z0-9]*${'$'}"""
                CaseFormatConverter.Format.LOWER_UNDERSCORE -> """^([a-z]|\d)+(_([a-z]|\d)+)*${'$'}"""
                CaseFormatConverter.Format.UPPER_UNDERSCORE -> """^([A-Z]|\d)+(_([A-Z]|\d)+)*${'$'}"""
            }.toRegex().matches(str)

            if (!isValid) {
                throw IllegalArgumentException("String $str is not of format $format")
            }
        }
    }
}