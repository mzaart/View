package utils.extensions

import utils.namingConventions.CaseFormatConverter
import utils.namingConventions.CaseFormatConverter.Format
import kotlin.reflect.KProperty

fun KProperty<*>.nameAsLowerUnderscore()
        = CaseFormatConverter.convert(Format.LOWER_CAMEL, Format.LOWER_UNDERSCORE, this.name)