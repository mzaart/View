package view.core.loaders.viewTree.nodes.nodeTestDsl

import view.core.loaders.viewTree.nodes.LayoutNode
import view.core.loaders.viewTree.nodes.Node
import kotlin.properties.Delegates

open class NodeImpl: Node() {

    override var content: Map<String, Any> by Delegates.notNull()
}

class LayoutNodeImpl: LayoutNode() {

    override var content: Map<String, Any> by Delegates.notNull()
    override var children: List<Node> by Delegates.notNull()
}