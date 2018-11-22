package core.loaders.viewTree.nodes

/**
 * Represents the base class for all Node objects.
 *
 * A node object is an abstraction of a View serialization. It is a collection of parsed key-value pairs.
 */
abstract class Node {

    /**
     * The key-value pairs parsed
     */
    abstract var content: Map<String, Any>
}