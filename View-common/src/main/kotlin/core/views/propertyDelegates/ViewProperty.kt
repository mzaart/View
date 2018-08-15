package core.views.propertyDelegates

import core.views.HasId
import utils.validators.Validator
import kotlin.reflect.KProperty

class ViewProperty<T: Any>(
        private var value: T,
        private val validator: Validator<T>? = null
): AbstractViewProperty<T>(value) {

    override operator fun setValue(thisRef: HasId, property: KProperty<*>, value: T) {
        validator?.validate(value)
        this.value = value
        renderer.invalidate(thisRef.id)
    }
}