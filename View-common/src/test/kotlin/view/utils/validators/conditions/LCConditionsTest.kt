package view.utils.validators.conditions

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LCConditions {

    @Test
    fun testRGBValid() {
        assertTrue { LC.RGB.isValid(0xFFFFFF) }
    }

    @Test
    fun testRGBInvalidTooShort() {
        assertFalse { LC.RGB.isValid(-1) }
    }

    @Test
    fun testRGBInvalidTooLong() {
        assertFalse { LC.RGB.isValid(0xFFFFFFF) }
    }

    @Test
    fun testRGBAValid() {
        assertTrue { LC.RGBA.isValid(0xFFFFFFFF) }
    }

    @Test
    fun testRGBAInvalidTooShort() {
        assertFalse { LC.RGBA.isValid(-1) }
    }

    @Test
    fun testRGBAInvalidTooLong() {
        assertFalse { LC.RGBA.isValid(0xFFFFFFFFF) }
    }

    @Test
    fun testColorValidRGB() {
        assertTrue { LC.COLOR.isValid(0xFFFFFF) }
    }

    @Test
    fun testColorValidRGBA() {
        assertTrue { LC.COLOR.isValid(0xFFFFFFFF) }
    }

    @Test
    fun testColorInvalidTooShort() {
        assertFalse { LC.COLOR.isValid(-1) }
    }

    @Test
    fun testColorInvalidTooLong() {
        assertFalse { LC.COLOR.isValid(0xFFFFFFFFF) }
    }
}