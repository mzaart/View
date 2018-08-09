package core.loaders.builders.layouts

import core.loaders.InvalidViewTreeException
import core.loaders.Key
import core.views.layouts.LinearLayout
import utils.toLowerUnderscore

open class LinearLayoutBuilder: LayoutBuilder<LinearLayout>() {

    enum class Keys: Key {
        DIRECTION
    }

    override val view = LinearLayout()
    override val requiredKeys = super.requiredKeys + setOf(Keys.DIRECTION)

    override fun applyAttributes(attributes: Map<String, String>): LinearLayout {
        checkRequiredKeys(attributes.keys)
        val layout = super.applyAttributes(attributes)
        layout.direction = LinearLayout.Direction.values().firstOrNull {
            v -> v.toLowerUnderscore() == attributes[Keys.DIRECTION.getName()]!!
        } ?: throw InvalidViewTreeException(Keys.DIRECTION)
        return layout
    }
}