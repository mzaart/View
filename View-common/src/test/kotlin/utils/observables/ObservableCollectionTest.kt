package utils.observables

import kotlin.test.Test
import kotlin.test.assertEquals

class ObservableCollectionTest {

    @Test
    fun testAdd() {
        testListenerCalled { it.add(1) }
    }

    @Test
    fun testAddAll() {
        testListenerCalled { it.addAll(listOf(1, 2, 3)) }
    }

    @Test
    fun testClear() {
        testListenerCalled(2) {
            it.addAll(listOf(1, 2, 3))
            it.clear()
        }
    }

    @Test
    fun testRemove() {
        testListenerCalled(2) {
            it.add(1)
            it.remove(1)
        }
    }

    @Test
    fun testRemoveAll() {
        testListenerCalled(2) {
            it.addAll(listOf(1, 2, 3))
            it.removeAll(listOf(1, 2))
        }
    }

    @Test
    fun testRetainAll() {
        testListenerCalled(2) {
            it.addAll(listOf(1, 2, 3))
            it.retainAll(listOf(1, 2))
        }
    }

    private fun testListenerCalled(times: Int = 1, collectionOp: (ObservableCollection<Int>) -> Unit) {
        var listenerCalled = 0
        val collection = ObservableCollection<Int> { listenerCalled++ }
        collectionOp(collection)
        assertEquals(times, listenerCalled)
    }
}