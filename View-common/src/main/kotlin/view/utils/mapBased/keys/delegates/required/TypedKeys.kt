package view.utils.mapBased.keys.delegates.required

import view.utils.extensions.representations

object RequiredIntRWKey: RequiredRWKey<Int>({ it.toInt() })
object RequiredDoubleRWKey: RequiredRWKey<Double>({ it.toDouble() })
object RequiredBoolRWKey: RequiredRWKey<Boolean>({ it.toBoolean() })
object RequiredStringRWKey: RequiredRWKey<String>({ it })

class RequiredEnumRWKey<E: Enum<E>>(private val values: Array<E>): RequiredRWKey<E>({ strVal ->
    values.first { enumVal -> strVal in enumVal.representations() }
})