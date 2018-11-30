package view.utils.mapBased.keys.delegates

import view.core.loaders.builders.ViewKeys
import view.utils.mapBased.keys.delegates.nullable.NullableRWKey
import view.utils.mapBased.keys.delegates.required.RequiredRWKey

class KeysStub: ViewKeys() {

    var requiredKey by RequiredRWKey { it.toBoolean() }
    var nullableKey by NullableRWKey { it.toBoolean() }
}