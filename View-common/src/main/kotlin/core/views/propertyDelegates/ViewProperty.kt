package core.views.propertyDelegates

import core.views.View
import utils.validators.Validator
import kotlin.reflect.KProperty

// todo come up with a way to avoid autoboxing when using primitive view props
class ViewProperty<T: Any>(
        value: T,
        private val validator: Validator<T>? = null
): AbstractViewProperty<T>(value) {

    override operator fun setValue(thisRef: View, property: KProperty<*>, value: T) {
        validator?.validate(value)
        this.value = value
        renderer.invalidate(thisRef.id)
    }
}