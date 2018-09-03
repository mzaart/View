package core.views.propertyDelegates

import core.renderers.ViewRenderer
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import testUtils.kodein.TestKodein
import utils.validators.ValidationException
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ViewPropertyTest {

    @BeforeTest
    fun clearDependencies() {
        TestKodein.reset()
    }

    @Test
    fun testSetGet() {
        val view = ViewStub()
        assertEquals(ViewStub.VIEW_PROP_INITIAL_VAL, view.viewProp)

        val newVal = 2
        view.viewProp = newVal
        assertEquals(newVal, view.viewProp)
    }

    @Test
    fun testViewRendererUpdated() {
        TestKodein.addConfig(Kodein {
            bind<ViewRenderer>() with singleton { MockRenderer(ViewStub.ID) }
        })

        ViewStub().viewProp = 2
    }

    @Test
    fun testValidationPassed() {
        ViewStub().validationViewProp = 2
    }

    @Test
    fun testValidationError() {
        assertFailsWith(ValidationException::class) {
            ViewStub().validationViewProp = -1
        }
    }
}