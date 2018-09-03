package core.loaders.keys.delegates.nullable

object ColorKey: Key<Long>({ s -> s.toLong(16) })