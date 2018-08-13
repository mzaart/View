package core.loaders.keys.delegates.required

import utils.extensions.lowerUnderscoreToEnum

class RequiredIntKey: RequiredKey<Int>({ it.toInt() })
class RequiredDoubleKey: RequiredKey<Double>({ it.toDouble() })
class RequiredBoolKey: RequiredKey<Boolean>({ it.toBoolean() })
class RequiredStringKey: RequiredKey<String>({ it })
class RequiredEnumKey<E: Enum<E>>: RequiredKey<E>({ it.lowerUnderscoreToEnum() })