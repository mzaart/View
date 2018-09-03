package core.views.propertyDelegates

import core.renderers.ViewRenderer
import core.views.events.EventListener
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import testUtils.kodein.TestKodein
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EventListenerTest {

    private val listener: EventListener = { val nothing = 123455 }

    @BeforeTest
    fun resetDependencies() {
        TestKodein.reset()
    }

    @Test
    fun testGetSet() {
        val view = ViewStub()
        view.onEvent = listener
        assertEquals(listener, view.onEvent)
    }

    @Test
    fun testViewRendererUpdated() {
        TestKodein.addConfig(Kodein {
            bind<ViewRenderer>() with singleton { MockRenderer(
                    ViewStub.ID,
                    ViewStub.TestEvents.EVENT_A,
                    listener
            ) }
        })
        ViewStub().onEvent = listener
    }
}