package core.loaders.keys.delegates.required

import utils.extensions.representations

object RequiredIntKey: RequiredKey<Int>({ it.toInt() })
object RequiredDoubleKey: RequiredKey<Double>({ it.toDouble() })
object RequiredBoolKey: RequiredKey<Boolean>({ it.toBoolean() })
object RequiredStringKey: RequiredKey<String>({ it })

class RequiredEnumKey<E: Enum<E>>(private val values: Array<E>): RequiredKey<E>({ strVal ->
    values.first { enumVal -> strVal in enumVal.representations() }
})