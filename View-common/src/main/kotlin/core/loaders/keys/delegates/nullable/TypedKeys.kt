package core.loaders.keys.delegates.nullable

import utils.extensions.toLowerUnderscore


class IntKey(vararg conflicts: String): Key<Int>({ it.toInt() }, *conflicts)
class DoubleKey(vararg conflicts: String): Key<Double>({ it.toDouble() }, *conflicts)
class BoolKey(vararg conflicts: String): Key<Boolean>({ it.toBoolean() }, *conflicts)
class StringKey(vararg conflicts: String): Key<String>({ it }, *conflicts)

class EnumKey<E: Enum<E>>(private val enumVals: Array<E>, vararg conflicts: String): Key<E>({
    enumVals.first { enumVal -> enumVal.toLowerUnderscore() == it }
}, *conflicts)