package utils.extensions

import utils.namingConventions.CaseFormatConverter

fun String.toID() = this.hashCode()

fun String.lowerCamelToLowerUnderscore()
        = CaseFormatConverter.convert(CaseFormatConverter.Format.LOWER_CAMEL, CaseFormatConverter.Format.LOWER_UNDERSCORE, this)