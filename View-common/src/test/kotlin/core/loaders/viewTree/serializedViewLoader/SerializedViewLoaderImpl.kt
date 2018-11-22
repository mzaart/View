package core.loaders.viewTree.serializedViewLoader

import core.loaders.viewTree.treeLoaders.SerializedViewTreeLoader
import core.loaders.viewTree.nodes.Node

class SerializedViewLoaderImpl(private val root: Node): SerializedViewTreeLoader() {

    override fun getRootNode() = root
}