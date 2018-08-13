package utils.extensions

import utils.namingConventions.CaseFormatConverter
import utils.namingConventions.CaseFormatConverter.Format

fun <E : Enum<E>> E.toLowerUnderscore()
        = CaseFormatConverter.convert(Format.UPPER_UNDERSCORE, Format.LOWER_UNDERSCORE, this.toString())

fun <E : Enum<E>> String.lowerUnderscoreToEnum(): E
        = enumValueOf(CaseFormatConverter.convert(Format.LOWER_UNDERSCORE, Format.UPPER_UNDERSCORE, this))

fun <E : Enum<E>> String.lowerUnderscoreIsEnum(): Boolean = try {
    this.lowerUnderscoreToEnum<E>()
    true
} catch (e: RuntimeException) {
    false
}