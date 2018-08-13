package core.loaders.keys.delegates.nullable

import utils.extensions.toID

class IdKey: Key<Int>({ it.toID() })