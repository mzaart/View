package core.loaders.keys.delegates.nullable

class ColorKey: Key<Int>({ s -> s.toInt(16) })