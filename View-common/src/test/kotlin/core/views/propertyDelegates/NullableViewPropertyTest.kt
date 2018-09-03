package core.views.propertyDelegates

import core.renderers.ViewRenderer
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import testUtils.kodein.TestKodein
import utils.validators.ValidationException
import kotlin.test.*

class NullableViewPropertyTest {

    @BeforeTest
    fun resetDependencies() {
        TestKodein.reset()
    }

    @Test
    fun getSet() {
        val view = ViewStub()
        assertNull(view.nullableViewProp)

        val newVal = 2
        view.nullableViewProp = newVal
        assertEquals(newVal, view.nullableViewProp)
    }

    @Test
    fun testViewRendererUpdated() {
        TestKodein.addConfig(Kodein {
            bind<ViewRenderer>() with singleton { MockRenderer(ViewStub.ID) }
        })
        ViewStub().nullableViewProp = 2
    }

    @Test
    fun testValidationPassed() {
        ViewStub().nullableValidationViewProperty = 2
    }

    @Test
    fun testValidationError() {
        assertFailsWith<ValidationException> {
            ViewStub().nullableValidationViewProperty = -1
        }
    }
}