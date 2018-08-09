package core.loaders

class InvalidViewTreeException(message: String): RuntimeException(message) {

    constructor(missingKeys: Set<String>): this("Missing keys: $missingKeys")
    constructor(invalidKey: Key): this("Invalid key $invalidKey")
}