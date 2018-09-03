package core.loaders.viewTree.serializedViewLoader

import core.loaders.viewTree.nodes.nodeTestDsl.layoutNode
import core.loaders.viewTree.nodes.nodeTestDsl.node
import core.views.display.TextView
import core.views.layouts.LinearLayout
import core.views.layouts.RelativeLayout
import kotlin.test.Test
import kotlin.test.assertTrue

class ViewTreeTraversalTest {

    @Test
    fun testRootDepthIsOne() {
        val root = node {
            content = mapOf(
                    "id" to "tv",
                    "type" to "TextView"
            )
        }
        val loadedVt = SerializedViewLoaderImpl(root).loadViewTree()

        assertTrue(loadedVt is LinearLayout)
        assertTrue(loadedVt.children().size == 1)
        assertTrue(loadedVt.children()[0] is TextView)
    }

    @Test
    fun testDepthIsThree() {
        val root = layoutNode {
            content = mapOf(
                    "id" to "1",
                    "type" to "LinearLayout",
                    "direction" to "HORIZONTAL"
            )

            children = listOf(
                    node {
                        content = mapOf(
                                "id" to "2",
                                "type" to "TextView"
                        )
                    },

                    layoutNode {
                        content = mapOf(
                                "id" to "3",
                                "type" to "RelativeLayout"
                        )

                        children = listOf(node {
                            content = mapOf(
                                    "id" to "4",
                                    "type" to "TextView",
                                    "center" to "true"
                            )
                        })
                    }
            )
        }
        val loadedVt = SerializedViewLoaderImpl(root).loadViewTree()

        assertTrue(loadedVt is LinearLayout)
        assertTrue(loadedVt.children().size == 1)

        val child1 = loadedVt.children()[0]
        assertTrue(child1 is LinearLayout)
        assertTrue((child1 as LinearLayout).direction == LinearLayout.Direction.HORIZONTAL)
        assertTrue(child1.children().size == 2)

        val tvChild = child1.children()[0]
        assertTrue(tvChild is TextView)

        val layoutChild = child1.children()[1]
        assertTrue(layoutChild is RelativeLayout)
        assertTrue((layoutChild as RelativeLayout).children().size == 1)
        assertTrue(layoutChild.children()[0] is TextView)
    }
}