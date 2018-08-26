package core.views.style

import utils.validators.Validator
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StyleAttr<S: Style, T: Any>(private val validator: Validator<T>? = null): ReadWriteProperty<S, T?> {

    override fun getValue(thisRef: S, property: KProperty<*>): T?
        = thisRef.styleAttrs[property.name] as T?

    override fun setValue(thisRef: S, property: KProperty<*>, value: T?) {
        if (value != null) {
            validator?.validate(value)
            thisRef.styleAttrs[property.name] = value
        }
    }
}