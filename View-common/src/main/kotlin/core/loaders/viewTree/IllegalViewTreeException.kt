package core.loaders.viewTree

/**
 * This exception is thrown when an invalid view tree is encountered.
 *
 * A view tree can be invalid for numerous reasons such as having a view missing required properties or having
 * a duplicate ID.
 *
 * @constructor Initializes the exception with a message
 * @param message The reason for the tree being invalid
 */
class IllegalViewTreeException(message: String): RuntimeException(message) {

    /**
     * @param missingKeys The missing keys that are required for building the view
     */
    constructor(missingKeys: Set<String>): this("Missing keys: $missingKeys")
}