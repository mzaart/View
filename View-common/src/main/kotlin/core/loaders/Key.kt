package core.loaders

import utils.namingConventions.CaseFormatConverter
import utils.namingConventions.CaseFormatConverter.Format

interface Key {

    fun getName() = CaseFormatConverter.convert(Format.UPPER_UNDERSCORE, Format.LOWER_UNDERSCORE, this.toString())
}