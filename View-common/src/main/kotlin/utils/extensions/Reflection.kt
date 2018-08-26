package utils.extensions

import utils.namingConventions.CaseFormatConverter
import utils.namingConventions.CaseFormatConverter.Format
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

fun KClass<*>.nameAsSnakeFormat()
        = CaseFormatConverter.convert(Format.UPPER_CAMEL, Format.LOWER_UNDERSCORE, this.simpleName!!)