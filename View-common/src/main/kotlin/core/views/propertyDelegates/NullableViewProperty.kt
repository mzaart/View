package core.views.propertyDelegates

import core.views.View
import utils.validators.Validator
import kotlin.reflect.KProperty

class NullableViewProperty<T: Any>(
        private var value: T? = null,
        private val validator: Validator<T>? = null
): AbstractViewProperty<T?>(value) {

    override fun setValue(thisRef: View, property: KProperty<*>, value: T?) {
        if (value != null) {
            validator?.validate(value)
        }
        this.value = value
        renderer.invalidate(thisRef.id)
    }
}