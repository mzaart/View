package utils.extensions

import utils.namingConventions.CaseFormatConverter
import utils.namingConventions.CaseFormatConverter.Format

/**
 * Returns the enum value's string representation in multiple formats.
 *
 * For a list of formats, see [CaseFormatConverter.Format].
 *
 * @return Enum value's string representation in multiple formats.
 */
fun <E: Enum<E>> E.representations() = Format.values().map {
    CaseFormatConverter.convert(Format.UPPER_UNDERSCORE, it, this.toString())
}

/**
 * Returns the enum value that matches the string representation.
 *
 * Note that string representation should be in one of the following [formats][Format].
 *
 * @param values The available enum values
 * @return The enum value matching the string representation
 */
fun <E: Enum<E>> String.toEnum(values: Array<E>): E = values.first { enumVal ->
    this in enumVal.representations()
}