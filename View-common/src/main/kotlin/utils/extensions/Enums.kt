package utils.extensions

import utils.namingConventions.CaseFormatConverter
import utils.namingConventions.CaseFormatConverter.Format

fun <E: Enum<E>> E.representations() = Format.values().map {
    CaseFormatConverter.convert(Format.UPPER_UNDERSCORE, it, this.toString())
}