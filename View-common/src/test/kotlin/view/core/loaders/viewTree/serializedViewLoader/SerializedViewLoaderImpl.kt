package view.core.loaders.viewTree.serializedViewLoader

import view.core.loaders.viewTree.treeLoaders.SerializedViewTreeLoader
import view.core.loaders.viewTree.nodes.Node

class SerializedViewLoaderImpl(private val root: Node): SerializedViewTreeLoader() {

    override fun getRootNode() = root
}