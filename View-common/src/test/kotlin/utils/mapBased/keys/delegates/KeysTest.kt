package utils.mapBased.keys.delegates

import core.loaders.viewTree.IllegalViewTreeException
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue

class KeysTest {

    @Test
    fun testRequiredGetNull() {
        val stub = KeysStub()
        assertFailsWith<IllegalViewTreeException> {
            stub.requiredKey
        }
    }

    @Test
    fun testRequiredGetVal() {
        val stub = KeysStub()
        stub.requiredKey = true
        assertTrue(stub.requiredKey)
    }

    @Test
    fun testRequiredGetFromString() {
        val stub = KeysStub()
        stub.keys = mutableMapOf("requiredKey" to "true")
        assertTrue(stub.requiredKey)
    }

    @Test
    fun testGetNull() {
       assertNull(KeysStub().nullableKey)
    }

    @Test
    fun testGetVal() {
        val stub = KeysStub()
        stub.nullableKey = true
        assertTrue(stub.nullableKey!!)
    }

    @Test
    fun testGetFromString() {
        val stub = KeysStub()
        stub.keys = mutableMapOf("nullableKey" to "true")
        assertTrue(stub.nullableKey!!)
    }
}