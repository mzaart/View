package view.core.loaders.builders

import view.core.loaders.viewTree.IllegalViewTreeException
import view.core.views.display.TextView
import kotlin.test.Test
import kotlin.test.assertFailsWith

class ViewBuilderKeyConflictTest {

    class ViewBuilderStub: AbstractViewBuilder<TextView>() {

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