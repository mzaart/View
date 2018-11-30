package view.core.views.propertyDelegates

import view.core.views.View
import view.core.views.events.Event
import view.utils.validators.Validator
import view.utils.validators.conditions.IC

class ViewStub: View() {

    enum class TestEvents: Event {
        EVENT_A
    }

    companion object {
        const val ID = 1
        const val VIEW_PROP_INITIAL_VAL = 1
    }

    var viewProp: Int by ViewProperty(VIEW_PROP_INITIAL_VAL)
    var validationViewProp: Int by ViewProperty(1, Validator(IC.POSITIVE))
    var nullableViewProp: Int? by NullableViewProperty()
    var nullableValidationViewProperty: Int? by NullableViewProperty(Validator(IC.POSITIVE))
    var onEvent by EventListener(TestEvents.EVENT_A)
    var lateInitVal: Int by LateInitVal()

    init {
        id = ID
    }
}