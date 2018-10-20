package utils.mapBased.keys.delegates.nullable

object ColorRWKey: NullableRWKey<Long>({ s -> s.toLong(16) })