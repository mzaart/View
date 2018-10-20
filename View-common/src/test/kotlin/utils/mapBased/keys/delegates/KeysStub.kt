package utils.mapBased.keys.delegates

import core.loaders.builders.ViewKeys
import utils.mapBased.keys.delegates.nullable.NullableRWKey
import utils.mapBased.keys.delegates.required.RequiredRWKey

class KeysStub: ViewKeys() {

    var requiredKey by RequiredRWKey { it.toBoolean() }
    var nullableKey by NullableRWKey { it.toBoolean() }
}