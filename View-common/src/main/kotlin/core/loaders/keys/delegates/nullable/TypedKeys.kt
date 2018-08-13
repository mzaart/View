package core.loaders.keys.delegates.nullable

import utils.extensions.lowerUnderscoreToEnum

class IntKey: Key<Int>({ it.toInt() })
class DoubleKey: Key<Double>({ it.toDouble() })
class BoolKey: Key<Boolean>({ it.toBoolean() })
class StringKey: Key<String>({ it })
class EnumKey<E: Enum<E>>: Key<E>({ it.lowerUnderscoreToEnum() })