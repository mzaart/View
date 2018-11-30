package view.utils.mapBased.keys.delegates.nullable

import view.utils.extensions.representations

object IntRWKey: NullableRWKey<Int>({ it.toInt() })
object DoubleRWKey: NullableRWKey<Double>({ it.toDouble() })
object BoolRWKey: NullableRWKey<Boolean>({ it.toBoolean() })
object StringRWKey: NullableRWKey<String>({ it })

class EnumRWKey<E: Enum<E>>(private val enumVals: Array<E>): NullableRWKey<E>({ strVal ->
    enumVals.first { enumVal -> strVal in enumVal.representations() }
})