package view.utils.validators.conditions

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SCConditionsTest {

    @Test
    fun testEmptyValid() {
        assertTrue { SC.EMPTY.isValid("") }
    }

    @Test
    fun testEmptyInvalid() {
        assertFalse { SC.EMPTY.isValid("!!") }
    }

    @Test
    fun testPresentValid() {
        assertTrue { SC.PRESENT.isValid("!!") }
    }

    @Test
    fun testPresentInvalidEmpty() {
        assertFalse { SC.PRESENT.isValid("") }
    }

    @Test
    fun testPresentInvalidWhiteSpace() {
        assertFalse { SC.PRESENT.isValid(" ") }
        assertFalse { SC.PRESENT.isValid("\t") }
        assertFalse { SC.PRESENT.isValid("\n") }
    }

    @Test
    fun testUpperUnderscoreValid() {
        assertTrue { SC.UPPER_UNDERSCORE.isValid("UPPER_UNDERSCORE") }
    }

    @Test
    fun testUpperUnderscoreInvalidEmpty() {
        assertFalse { SC.UPPER_UNDERSCORE.isValid("") }
    }

    @Test
    fun testUpperUnderscoreInvalidNonEmpty() {
        assertFalse { SC.UPPER_UNDERSCORE.isValid("upper_underscore") }
        assertFalse { SC.UPPER_UNDERSCORE.isValid("Upper_underscore") }
        assertFalse { SC.UPPER_UNDERSCORE.isValid("upper_Underscore") }
        assertFalse { SC.UPPER_UNDERSCORE.isValid("UPPer_UNDERSCore") }
    }

    @Test
    fun testLowerUnderscoreValid() {
        assertTrue { SC.LOWER_UNDERSCORE.isValid("lower_underscore") }
    }

    @Test
    fun testLowerUnderscoreInvalidEmpty() {
        assertFalse { SC.LOWER_UNDERSCORE.isValid("") }
    }

    @Test
    fun testLowerUnderscoreInvalidNonEmpty() {
        assertFalse { SC.LOWER_UNDERSCORE.isValid("not_Valid") }
        assertFalse { SC.LOWER_UNDERSCORE.isValid("Not_valid") }
        assertFalse { SC.LOWER_UNDERSCORE.isValid("Not_Valid") }
        assertFalse { SC.LOWER_UNDERSCORE.isValid("not valid") }
    }

    @Test
    fun testUpperCamelValid() {
        assertTrue { SC.UPPER_CAMEL.isValid("UpperCamel") }
    }

    @Test
    fun testUpperCamelInvalidEmpty() {
        assertFalse { SC.UPPER_CAMEL.isValid("") }
    }

    @Test
    fun testUpperCamelInvalidNonEmpty() {
        assertFalse { SC.UPPER_CAMEL.isValid("upperCamel") }
        assertFalse { SC.UPPER_CAMEL.isValid("uppercamel") }
    }

    @Test
    fun testLowerCamelValid() {
        assertTrue { SC.LOWER_CAMEL.isValid("lowerCamel") }
    }

    @Test
    fun testLowerCamelInvalidEmpty() {
        assertFalse { SC.LOWER_CAMEL.isValid("") }
    }

    @Test
    fun testLowerCamelInvalidNonEmpty() {
        assertFalse { SC.LOWER_CAMEL.isValid("LowerCamel") }
    }
}
