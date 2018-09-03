package core.loaders.keys.delegates.nullable

import utils.extensions.representations

object IntKey: Key<Int>({ it.toInt() })
object DoubleKey: Key<Double>({ it.toDouble() })
object BoolKey: Key<Boolean>({ it.toBoolean() })
object StringKey: Key<String>({ it })

class EnumKey<E: Enum<E>>(private val enumVals: Array<E>): Key<E>({ strVal ->
    enumVals.first { enumVal -> strVal in enumVal.representations() }
})