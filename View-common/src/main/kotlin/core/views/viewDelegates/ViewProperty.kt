package core.views.viewDelegates

import utils.validators.Validator
import core.views.View
import kotlin.reflect.KProperty

class ViewProperty<T: Any>(
        private var value: T,
        private val validator: Validator<T>? = null
): AbstractViewProperty<T>(value) {

    override operator fun setValue(thisRef: View, property: KProperty<*>, value: T) {
        validator?.validate(value)
        this.value = value
        renderer.invalidate(thisRef.id)
    }
}