package utils.serializers

interface JsonSerializer<T> {

    fun serialize(): String
    fun deserialize(): T
}