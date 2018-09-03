package core.loaders.viewTree.nodes.nodeTestDsl

import core.loaders.viewTree.nodes.LayoutNode
import core.loaders.viewTree.nodes.Node
import kotlin.properties.Delegates

open class NodeImpl: Node() {

    override var content: Map<String, String> by Delegates.notNull()
}

class LayoutNodeImpl: LayoutNode() {

    override var content: Map<String, String> by Delegates.notNull()
    override var children: List<Node> by Delegates.notNull()
}