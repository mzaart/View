package core.loaders.keys.delegates.required

import utils.extensions.toLowerUnderscore

class RequiredIntKey(vararg conflicts: String): RequiredKey<Int>({ it.toInt() }, *conflicts)
class RequiredDoubleKey(vararg conflicts: String): RequiredKey<Double>({ it.toDouble() }, *conflicts)
class RequiredBoolKey(vararg conflicts: String): RequiredKey<Boolean>({ it.toBoolean() }, *conflicts)
class RequiredStringKey(vararg conflicts: String): RequiredKey<String>({ it }, *conflicts)

class RequiredEnumKey<E: Enum<E>>(private val values: Array<E>, vararg conflicts: String): RequiredKey<E>({
    values.first { enumVal -> enumVal.toLowerUnderscore() == it }
}, *conflicts)