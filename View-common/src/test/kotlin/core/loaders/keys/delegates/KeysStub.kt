package core.loaders.keys.delegates

import core.loaders.keys.ViewKeys
import core.loaders.keys.delegates.nullable.Key
import core.loaders.keys.delegates.required.RequiredKey

class KeysStub: ViewKeys() {

    var requiredKey by RequiredKey { it.toBoolean() }
    var nullableKey by Key { it.toBoolean() }
}