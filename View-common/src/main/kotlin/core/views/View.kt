package core.views

import core.views.events.ViewEvents
import core.views.layouts.Layout
import core.views.propertyDelegates.*
import utils.mapBased.keys.HasKeys
import utils.validators.Validator
import utils.validators.conditions.DC
import utils.validators.conditions.LC

abstract class View {

    enum class Visibility {
        VISIBLE,
        INVISIBLE,
        GONE
    }

    val name: String
        get() = this::class.simpleName!!

    var id: Int by LateInitVal()

    var width: Double by ViewProperty(Dimension.value(Dimension.Type.WRAP_CONTENT))
    var height: Double by ViewProperty(Dimension.value(Dimension.Type.WRAP_CONTENT))

    var parent: Layout? by NullableViewProperty()

    var disabled: Boolean by ViewProperty(false)
    var visibility: Visibility by ViewProperty(Visibility.VISIBLE)

    var marginTop: Double by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))
    var marginBottom: Double by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))
    var marginStart: Double by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))
    var marginEnd: Double by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))

    var paddingTop: Double by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))
    var paddingBottom: Double by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))
    var paddingStart: Double by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))
    var paddingEnd: Double by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))

    var backgroundColor: Long? by NullableViewProperty(Validator(LC.COLOR))

    var onClickListener by EventListener(ViewEvents.ON_CLICK)
    var onLongClickListener by EventListener(ViewEvents.ON_LONG_CLICK)
    var onResize by EventListener(ViewEvents.ON_RESIZE)

    var webExtras: HasKeys? = null
    var androidExtras: HasKeys? = null
    var iosExtras: HasKeys? = null
}