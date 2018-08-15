package core.loaders.viewTree

class IllegalViewTreeException(message: String): RuntimeException(message) {

    constructor(missingKeys: Set<String>): this("Missing keys: $missingKeys")
}