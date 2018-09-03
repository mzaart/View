package core.loaders.builders

import core.loaders.viewTree.IllegalViewTreeException
import core.views.View
import core.views.display.TextView
import kotlin.test.Test
import kotlin.test.assertFailsWith

class ViewBuilderKeyConflictTest {

    class ViewBuilderStub: ViewBuilder<TextView>() {

        override val view = TextView()
    }

    @Test
    fun testNoConflict() {
        val bldr = ViewBuilderStub()
        bldr.keys = mutableMapOf(
            "paddingTop" to "10"
        )
        bldr.build()
    }

    @Test
    fun testConflict() {
        val bldr = ViewBuilderStub()
        bldr.keys = mutableMapOf(
                "paddingTop" to "10",
                "paddingVertical" to "10"
        )
        assertFailsWith<IllegalViewTreeException> {
            bldr.build()
        }
    }
}