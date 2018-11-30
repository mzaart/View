package view.core.loaders.viewTree.nodes

import view.core.loaders.viewTree.nodes.deserializedNode.DeserializedLayoutNode
import view.core.loaders.viewTree.nodes.deserializedNode.DeserializedNode

/**
 * Represents the serialized layout view.
 */
abstract class LayoutNode: Node() {

    private val childrenKey = "children"

    /**
     * The node's child nodes
     */
    val children: List<Node>
        get() {
            if (isLayoutNode(content)) {
                throw IllegalArgumentException("Node has no children")
            }
            try {
                return (content[childrenKey]!! as List<Map<String, Any>>).map { content ->
                    if (isLayoutNode(content)) DeserializedLayoutNode(content) else DeserializedNode(content)
                }
            } catch (e: ClassCastException) {
                throw IllegalStateException("Invalid children")
            }
        }

    private fun isLayoutNode(map: Map<String, Any>) = !map.containsKey(childrenKey) || map[childrenKey] !is List<*>
}