package utils.mapBased.keys.delegates.nullable

import utils.mapBased.keys.MapInterface

/**
 * A delegate for mutable nullable properties that are based on maps and represent a RGB/RGBA color.
 *
 * The the retrieval strategy is defined as follows:
 *
 * [MapInterface.Strategy.STRING_BASED], [MapInterface.Strategy.CAST]
 */
object ColorRWKey: NullableRWKey<Long>({ s -> s.toLong(16) })