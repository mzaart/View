package utils.extensions

import utils.namingConventions.CaseFormatConverter
import utils.namingConventions.CaseFormatConverter.Format

fun String.toID() = this.hashCode()

fun String.lowerCamelToLowerUnderscore()
        = CaseFormatConverter.convert(Format.LOWER_CAMEL, Format.LOWER_UNDERSCORE, this)

fun String.formatRepresentations(current: Format): List<String>
        = Format.values().map { CaseFormatConverter.convert(current, it, this) }