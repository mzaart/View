package utils.validators.conditions

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DCConditionsTest {

    @Test
    fun testZeroValid() {
        assertTrue { DC.ZERO.isValid(0.0) }
    }

    @Test
    fun testZeroInvalid() {
        assertFalse{ DC.ZERO.isValid(1.0) }
    }

    @Test
    fun testPositiveValid() {
        assertTrue { DC.POSITIVE.isValid(1.0) }
    }

    @Test
    fun testPositiveInvalid() {
        assertFalse { DC.POSITIVE.isValid(-1.0) }
    }

    @Test
    fun testNegativeValid() {
        assertTrue { DC.NEGATIVE.isValid(-1.0) }
    }

    @Test
    fun testNegativeInvalid() {
        assertFalse { DC.NEGATIVE.isValid(1.0) }
    }

    @Test
    fun testNonPositiveValidZero() {
        assertTrue { DC.NON_POSITIVE.isValid(0.0) }
    }

    @Test
    fun testNonPositiveValidNegative() {
        assertTrue { DC.NON_POSITIVE.isValid(-1.0) }
    }

    @Test
    fun testNonPositiveInvalid() {
        assertFalse { DC.NON_POSITIVE.isValid(1.0) }
    }

    @Test
    fun testNonNegativeValidZero() {
        assertTrue { DC.NON_NEGATIVE.isValid(0.0) }
    }

    @Test
    fun testNonNegativeValidPositive() {
        assertTrue { DC.NON_NEGATIVE.isValid(1.0) }
    }

    @Test
    fun testNonNegativeInvalid() {
        assertFalse { DC.NON_NEGATIVE.isValid(-1.0) }
    }
}