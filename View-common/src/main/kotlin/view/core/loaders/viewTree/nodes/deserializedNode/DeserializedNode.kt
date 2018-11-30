package view.core.loaders.viewTree.nodes.deserializedNode

import view.core.loaders.viewTree.nodes.Node

/**
 * Is a collection of parsed key-value pairs.
 *
 * This node doesn't do any parsing, it just holds already parsed values.
 *
 * @constructor Initializes the node with the parsed key-value pairs
 * @param content The parsed key-value pairs
 */
class DeserializedNode(override var content: Map<String, Any>) : Node()
