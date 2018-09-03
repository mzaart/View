package utils.validators.conditions

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ICConditionsTest {

    @Test
    fun testZeroValid() {
        assertTrue { IC.ZERO.isValid(0) }
    }

    @Test
    fun testZeroInvalid() {
        assertFalse{ IC.ZERO.isValid(1) }
    }

    @Test
    fun testPositiveValid() {
        assertTrue { IC.POSITIVE.isValid(1) }
    }

    @Test
    fun testPositiveInvalid() {
        assertFalse { IC.POSITIVE.isValid(-1) }
    }

    @Test
    fun testNegativeValid() {
        assertTrue { IC.NEGATIVE.isValid(-1) }
    }

    @Test
    fun testNegativeInvalid() {
        assertFalse { IC.NEGATIVE.isValid(1) }
    }

    @Test
    fun testNonPositiveValidZero() {
        assertTrue { IC.NON_POSITIVE.isValid(0) }
    }

    @Test
    fun testNonPositiveValidNegative() {
        assertTrue { IC.NON_POSITIVE.isValid(-1) }
    }

    @Test
    fun testNonPositiveInvalid() {
        assertFalse { IC.NON_POSITIVE.isValid(1) }
    }

    @Test
    fun testNonNegativeValidZero() {
        assertTrue { IC.NON_NEGATIVE.isValid(0) }
    }

    @Test
    fun testNonNegativeValidPositive() {
        assertTrue { IC.NON_NEGATIVE.isValid(1) }
    }

    @Test
    fun testNonNegativeInvalid() {
        assertFalse { IC.NON_NEGATIVE.isValid(-1) }
    }
}