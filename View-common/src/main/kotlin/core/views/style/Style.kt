package core.views.style

import core.views.HasId
import core.views.propertyDelegates.LateInitVal
import core.views.propertyDelegates.NullableViewProperty
import utils.extensions.hasAnnotation
import utils.extensions.hasProperty
import utils.extensions.properties
import utils.validators.Validator
import utils.validators.conditions.IC
import kotlin.reflect.KMutableProperty

open class Style: HasId {

    override var id: Int by LateInitVal()

    @StyleAttr
    var backgroundColor: Int? by NullableViewProperty(Validator(IC.COLOR))
    // todo add background image

    @StyleAttr
    var hasShadow: Boolean? by NullableViewProperty()


    fun extendStyle(style: Style) {
        style.properties().filter { it.hasAnnotation(StyleAttr::class) }.forEach { attr ->
            if (attr !is KMutableProperty<*>) {
                throw IllegalArgumentException("Style attribute should be mutable")
            }
            if (this.hasProperty(attr)) {
                val attrVal = attr.getter.call(style)
                if (attr.getter.call(this) == null) {
                    attr.setter.call(this, attrVal)
                }
            }
        }
    }
}