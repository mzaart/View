package utils.namingConventions

import utils.extensions.isUpperCase
import utils.validators.conditions.StringConditions

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
                Format.UPPER_UNDERSCORE to Format.LOWER_UNDERSCORE
                    -> convertUpperUnderscoreToLowerUnderscore(str)
                Format.LOWER_UNDERSCORE to Format.UPPER_UNDERSCORE
                    -> convertLowerUnderscoreToUpperUnderscore(str)
                Format.LOWER_CAMEL to Format.LOWER_UNDERSCORE
                    -> convertLowerCamelToLowerUnderscore(str)
                Format.UPPER_CAMEL to Format.LOWER_UNDERSCORE
                    -> convertUpperCamelToLowerUnderscore(str)
                else -> throw NotImplementedError("Conversion from $from to $to is not supported yet")
            }
        }

        private fun convertUpperUnderscoreToLowerUnderscore(str: String): String {
            isFormatValid(str, StringConditions.UPPER_UNDERSCORE)
            return str.toLowerCase()
        }

        private fun convertLowerUnderscoreToUpperUnderscore(str: String): String {
            isFormatValid(str, StringConditions.LOWER_UNDERSCORE)
            return str.toUpperCase()
        }

        private fun convertLowerCamelToLowerUnderscore(str: String): String {
            isFormatValid(str, StringConditions.LOWER_CAMEL)
            val bldr = StringBuilder()
            str.forEach { c ->
                if (c.isUpperCase()) {
                    bldr.append('_')
                }
                bldr.append(c.toLowerCase())
            }
            return bldr.toString()
        }

        private fun convertUpperCamelToLowerUnderscore(str: String): String {
            isFormatValid(str, StringConditions.UPPER_CAMEL)
            return if (str.length == 1) str[0].toLowerCase().toString() else {
                val lowerCamel = str[0].toLowerCase() + str.substring(1)
                return convertLowerCamelToLowerUnderscore(str.trimStart())
            }
        }

        private fun isFormatValid(str: String, format: StringConditions) {
            if (!format.isValid(str)) {
                throw IllegalArgumentException("String $str is not of format $format")
            }
        }
    }
}