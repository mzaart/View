package view.core.loaders.viewTree.nodes.jsonNode

import view.core.loaders.viewTree.nodes.Node
import view.utils.serialization.Json

/**
 * A node that parses key-value pairs from a Json view serialization.
 *
 * @constructor Initializes the node with a JSON string
 * @param jsonString The serialized view in JSON format
 */
class JsonNode(jsonString: String): Node() {

    override var content = Json().parse(jsonString)
}