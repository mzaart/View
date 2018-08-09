package utils

import utils.namingConventions.CaseFormatConverter
import utils.namingConventions.CaseFormatConverter.Format

fun String.toID() = this.hashCode()

fun <E : Enum<E>> E.toLowerUnderscore()
        = CaseFormatConverter.convert(Format.UPPER_UNDERSCORE, Format.LOWER_UNDERSCORE, this.toString())

fun <E : Enum<E>> String.lowerUnderscoreToEnum(): E
        = enumValueOf<E>(CaseFormatConverter.convert(Format.LOWER_UNDERSCORE, Format.UPPER_UNDERSCORE, this))

fun <E : Enum<E>> String.lowerUnderscoreIsEnum(): Boolean = try {
    this.lowerUnderscoreToEnum<E>()
    true
} catch (e: RuntimeException) {
    false
}