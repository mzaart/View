package utils.namingConventions

import utils.validators.conditions.StringConditions

/**
 * Converts strings between different case formats.
 *
 * @see CaseFormatConverter.Format For available formats
 */
class CaseFormatConverter {

    /**
     * Represents a string format
     */
    enum class Format {
        UPPER_UNDERSCORE,
        LOWER_UNDERSCORE,
        UPPER_CAMEL,
        LOWER_CAMEL
    }

    companion object {
        
        private val formatToMiddle: Map<Format, (String) -> List<String>> = mapOf(
                Format.UPPER_UNDERSCORE to { s -> s.toLowerCase().split('_') },
                Format.LOWER_UNDERSCORE to { s -> s.split('_') },
                Format.UPPER_CAMEL to { s ->
                    (s[0].toLowerCase() + s.substring(1)).split(Regex("(?=[A-Z])")).map { it.toLowerCase() }
                },
                Format.LOWER_CAMEL to { s -> s.split(Regex("(?=[A-Z])")).map { it.toLowerCase() } }
        )

        private val middleToFormat: Map<Format, (List<String>) -> String> = mapOf(
                Format.UPPER_UNDERSCORE to { m ->  m.joinToString("_").toUpperCase() },
                Format.LOWER_UNDERSCORE to { m ->  m.joinToString("_") },
                Format.UPPER_CAMEL to { m ->
                    m.joinToString("") {
                        it[0].toUpperCase() + it.substring(1)
                    }
                },
                Format.LOWER_CAMEL to { m ->
                    when (m.size) {
                        1 -> m[0]
                        else -> {
                            val head = m[0]
                            val tail = m.subList(1, m.size)
                            StringBuilder(head).append(tail.joinToString("") {
                                it[0].toUpperCase() + it.substring(1)
                            }).toString()
                        }
                    }
                }
        )

        /**
         * Converts a string form one case format to another.
         *
         * @param from The original case format
         * @param to The target case format
         * @str The string to be converted
         *
         * @throws IllegalArgumentException If the passed string is not in the format passed in [from].
         */
        fun convert(from: Format, to: Format, str: String): String {
            isFormatValid(str, from)
            return middleToFormat[to]!!(formatToMiddle[from]!!(str))
        }

        private fun isFormatValid(str: String, format: Format) {
            val condition = when (format) {
                Format.UPPER_UNDERSCORE -> StringConditions.UPPER_UNDERSCORE
                Format.LOWER_UNDERSCORE -> StringConditions.LOWER_UNDERSCORE
                Format.UPPER_CAMEL -> StringConditions.UPPER_CAMEL
                Format.LOWER_CAMEL -> StringConditions.LOWER_CAMEL
            }
            if (!condition.isValid(str)) {
                throw IllegalArgumentException("String $str is not of format $format")
            }
        }
    }
}