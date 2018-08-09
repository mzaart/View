package core.loaders.builders

import core.loaders.InvalidViewTreeException
import core.loaders.Key
import core.views.View

abstract class ViewBuilder<V: View> {

    enum class Keys: Key {
        ID,
        TYPE,
        VISIBILITY,
        WIDTH ,
        HEIGHT,
        DISABLED,
        IS_CARD,
        MARGIN_TOP,
        MARGIN_BOTTOM,
        MARGIN_START,
        MARGIN_END,
        MARGIN_HORIZONTAL,
        MARGIN_VERTICAL,
        PADDING_TOP,
        PADDING_BOTTOM,
        PADDING_START,
        PADDING_END,
        PADDING_HORIZONTAL,
        PADDING_VERTICAL
    }

    open val requiredKeys: Set<Key> = setOf()
    protected abstract val view: V

    fun build() = view

    open fun applyAttributes(attributes: Map<String, String>): V {
        checkRequiredKeys(attributes.keys)
        // TODO process common view attributes
        return view
    }

    fun checkRequiredKeys(keys: Set<String>) {
        if (keys.intersect(requiredKeys) != requiredKeys) {
            throw InvalidViewTreeException((requiredKeys - keys) as Set<String>)
        }
    }
}