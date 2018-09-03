package utils.namingConvensions

import utils.namingConventions.CaseFormatConverter
import utils.namingConventions.CaseFormatConverter.Format
import kotlin.test.Test
import kotlin.test.assertEquals

class CaseFormatConverterTest {

    private val expectedString = mapOf(
            Format.UPPER_UNDERSCORE to "EXPECTED_STRING",
            Format.LOWER_UNDERSCORE to "expected_string",
            Format.UPPER_CAMEL to "ExpectedString",
            Format.LOWER_CAMEL to "expectedString"
    )

    @Test
    fun testAllPermutations() {
        for (from in Format.values()) {
            for (to in Format.values()) {
                assertEquals(
                        expectedString[to]!!,
                        CaseFormatConverter.convert(from, to, expectedString[from]!!)
                )
            }
        }
    }
}