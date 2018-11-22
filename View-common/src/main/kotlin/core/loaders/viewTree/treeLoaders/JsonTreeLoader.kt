package core.loaders.viewTree.treeLoaders

import core.loaders.viewTree.nodes.jsonNode.JsonLayoutNode

/**
 * A tree loader that loads view trees from JSON.
 *
 * @constructor Initializes the loader with a JSON string
 * @param The view tree JSON string
 */
class JsonTreeLoader(private val jsonString: String): SerializedViewTreeLoader() {

    override fun getRootNode() = JsonLayoutNode(jsonString)
}