package core.loaders.keys.delegates.nullable

class ColorKey(vararg conflicts: String): Key<Int>({ s -> s.toInt(16) }, *conflicts)