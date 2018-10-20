package core.views

object Dimension {

    enum class Type {
        WRAP_CONTENT,
        RELATIVE,
        EXPLICIT
    }

    fun value(type: Type, value: Double? = null): Double {
        return when (type) {
            Dimension.Type.WRAP_CONTENT -> -1.0
            Dimension.Type.RELATIVE -> {
                if (value!! !in 0.0..1.0) {
                    throw IllegalArgumentException("Value should belong to ]0, 1]")
                }
                return value
            }
            Dimension.Type.EXPLICIT -> {
                if (value!! <= 1) {
                    throw IllegalArgumentException("Value should be greater than 1")
                }
                return value
            }
        }
    }

   fun type(value: Double): Type = when (value) {
       -1.0 -> Type.WRAP_CONTENT
       in 0.0..1.0 -> Type.RELATIVE
       in 1.1..Double.MAX_VALUE -> Type.EXPLICIT
       else -> throw IllegalArgumentException("Invalid value for dimension")
   }
}