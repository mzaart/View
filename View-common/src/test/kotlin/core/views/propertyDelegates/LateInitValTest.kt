package core.views.propertyDelegates

import testUtils.kodein.TestKodein
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LateInitValTest {

    @BeforeTest
    fun resetDependencies() {
        TestKodein.reset()
    }

    @Test
    fun testGetAfterSet() {
        val propVal = 1
        val view = ViewStub()
        view.lateInitVal = propVal
        assertEquals(propVal, view.lateInitVal)
    }

    @Test
    fun testGetBeforeSet() {
        assertFailsWith<IllegalStateException> { ViewStub().lateInitVal }
    }

    @Test
    fun testSettingValueTwice() {
        val view = ViewStub()
        view.lateInitVal = 1
        assertFailsWith<IllegalStateException> { view.lateInitVal = 2 }
    }
}