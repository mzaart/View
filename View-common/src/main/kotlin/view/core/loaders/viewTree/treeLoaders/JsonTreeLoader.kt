package view.core.loaders.viewTree.treeLoaders

import view.core.loaders.viewTree.nodes.jsonNode.JsonLayoutNode

/**
 * A tree loader that loads view trees from JSON.
 *
 * @constructor Initializes the loader with a JSON string
 * @param jsonString The view tree JSON string
 */
class JsonTreeLoader(jsonString: String): SerializedViewTreeLoader(JsonLayoutNode(jsonString))