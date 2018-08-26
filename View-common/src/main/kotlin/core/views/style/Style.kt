package core.views.style

import core.views.propertyDelegates.LateInitVal
import utils.validators.Validator
import utils.validators.conditions.IC

open class Style {

    var id: Int by LateInitVal()

    val styleAttrs: MutableMap<String, Any?> = mutableMapOf()

    var backgroundColor: Int? by StyleAttr(Validator(IC.COLOR))

    var hasShadow: Boolean? by StyleAttr()

    fun extendStyle(style: Style) {
        style.styleAttrs.keys.forEach { key ->
            if (key !in styleAttrs.keys) {
                styleAttrs[key] = style.styleAttrs[key]
            }
        }
    }
}