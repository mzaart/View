package utils.extensions

import kotlin.reflect.KClass
import utils.namingConventions.CaseFormatConverter
import utils.namingConventions.CaseFormatConverter.Format

fun <E : Enum<E>> E.toLowerUnderscore()
        = CaseFormatConverter.convert(Format.UPPER_UNDERSCORE, Format.LOWER_UNDERSCORE, this.toString())

fun <E : Enum<E>> String.lowerUnderscoreIsEnum(enumVals: Array<E>) = enumVals.any { it.toLowerUnderscore() == this }