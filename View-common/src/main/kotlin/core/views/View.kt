package core.views

import core.views.events.ViewEvents
import core.views.layouts.Layout
import core.views.propertyDelegates.*
import utils.validators.DNonNegative
import utils.validators.Validator

abstract class View {

    enum class Visibility {
        VISIBLE,
        INVISIBLE,
        GONE
    }

    var id: Int by LateInitVal()

    var width: Double? by NullableViewProperty()
    var height: Double? by NullableViewProperty()

    var parent: Layout? by NullableViewProperty()

    var disabled: Boolean by ViewProperty(false)
    var isCard: Boolean by ViewProperty(false)
    var visibility: Visibility by ViewProperty(Visibility.VISIBLE)

    var marginTop: Double by ViewProperty(0.0, Validator(DNonNegative()))
    var marginBottom: Double by ViewProperty(0.0, Validator(DNonNegative()))
    var marginStart: Double by ViewProperty(0.0, Validator(DNonNegative()))
    var marginEnd: Double by ViewProperty(0.0, Validator(DNonNegative()))

    var paddingTop: Double by ViewProperty(0.0, Validator(DNonNegative()))
    var paddingBottom: Double by ViewProperty(0.0, Validator(DNonNegative()))
    var paddingStart: Double by ViewProperty(0.0, Validator(DNonNegative()))
    var paddingEnd: Double by ViewProperty(0.0, Validator(DNonNegative()))

    var onClickListener by EventListener(ViewEvents.ON_CLICK)
    var onLongClickListener by EventListener(ViewEvents.ON_LONG_CLICK)
    var onResize by EventListener(ViewEvents.ON_RESIZE)
}