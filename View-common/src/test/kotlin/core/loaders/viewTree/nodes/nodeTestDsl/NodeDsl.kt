@file:Suppress("ClassName")

package core.loaders.viewTree.nodes.nodeTestDsl


object node {

    operator fun invoke(init: NodeImpl.() -> Unit): NodeImpl {
        val node = NodeImpl()
        node.init()
        return node
    }
}


object layoutNode {

    operator fun invoke(init: LayoutNodeImpl.() -> Unit): LayoutNodeImpl {
        val node = LayoutNodeImpl()
        node.init()
        return node
    }
}