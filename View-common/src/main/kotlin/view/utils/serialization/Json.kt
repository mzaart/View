package view.utils.serialization

expect class Json() {

    fun parse(jsonString: String): Map<String, Any>
    fun serialize(map: Map<String, Any>): String
}