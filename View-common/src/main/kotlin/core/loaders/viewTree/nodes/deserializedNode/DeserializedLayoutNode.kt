package core.loaders.viewTree.nodes.deserializedNode

import core.loaders.viewTree.nodes.LayoutNode

/**
 * Holds the parsed key-value pairs of a layout view serialization.
 *
 * This node doesn't do any parsing, it just holds already parsed key-value pairs.
 *
 * @constructor Initializes the node with parsed key-value pairs
 * @param content The parsed key-value pairs
 */
class DeserializedLayoutNode(override var content: Map<String, Any>) : LayoutNode()
