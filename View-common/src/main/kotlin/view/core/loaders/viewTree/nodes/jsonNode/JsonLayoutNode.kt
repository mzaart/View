package view.core.loaders.viewTree.nodes.jsonNode

import view.core.loaders.viewTree.nodes.LayoutNode
import view.utils.serialization.Json

/**
 * A node that parses key-value pairs from a Json layout view serialization.
 *
 * @constructor Initializes the node with a JSON string
 * @param jsonString The serialized layout in a JSON format
 */
class JsonLayoutNode(jsonString: String): LayoutNode() {

    override var content = Json().parse(jsonString)
}