package view.core.views

/**
 * The Dimension object contains utility methods for classifying values that represent dimensions such as a view's
 * width, height and margins.
 *
 * Each dimension value is mapped to a dimension type that specifies how the [ViewTreeRenderer][view.core.renderers.ViewTreeRenderer]
 * should interpret the value. For more information on available dimension types, please see [Dimension.Type].
 *
 * @see Dimension.Type
 */
object Dimension {

    /**
     * Represents the type of the dimension value.
     */
    enum class Type {

        /**
         * Forces the view's width/height to expand just enough to fit the width/height of it's content.
         *
         * The value for this dimension should be -1.0.
         */
        WRAP_CONTENT,

        /**
         * Sets the dimension as a percentage of the view's parent dimension.
         *
         * The value for this dimension should be in the range [0, 1].
         *
         * For example, a view having a value of 0.5 for it's width property will have a width half as wide as its
         * parent.
         */
        RELATIVE,

        /**
         * Sets the value of the dimension explicitly as is.
         *
         * The value for this dimension should be greater than one.
         *
         * Note that this value is unit-less, it is up to the [ViewTreeRenderer][view.core.renderers.ViewTreeRenderer] to
         * specify a unit.
         */
        EXPLICIT
    }

    /**
     * Validates that the passed value corresponds to the passed dimension type.
     *
     * @param type The dimension type that the value should belong to.
     * @param value The value to be checked.
     * @return The passed value or -1.0 if [Dimension.Type.WRAP_CONTENT] was passed.
     *
     * @throws IllegalArgumentException If the passed value doesn't correspond to the passed dimension type.
     */
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

    /**
     * Returns the type that corresponds to the dimension value.
     *
     * @param value The dimension value
     * @return The dimension type that corresponds to the value.
     *
     * @throws IllegalArgumentException  If the value does not correspond to any dimension type
     */
   fun type(value: Double): Type = when (value) {
       -1.0 -> Type.WRAP_CONTENT
       in 0.0..1.0 -> Type.RELATIVE
       in 1.1..Double.MAX_VALUE -> Type.EXPLICIT
       else -> throw IllegalArgumentException("Invalid value for dimension")
   }
}