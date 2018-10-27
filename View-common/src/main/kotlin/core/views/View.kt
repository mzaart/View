package core.views

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

    // todo add theme as view prop

    val name: String
        get() = this::class.simpleName!!

    var id: Int by LateInitVal()

    var width: Double by ViewProperty(Dimension.value(Dimension.Type.WRAP_CONTENT))
    var height: Double by ViewProperty(Dimension.value(Dimension.Type.WRAP_CONTENT))

    var parent: Layout? = null

    var disabled: Boolean by ViewProperty(false)
    var visibility: Visibility by ViewProperty(Visibility.VISIBLE)

    var marginTop: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))
    var marginBottom: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))
    var marginStart: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))
    var marginEnd: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))

    var paddingTop: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))
    var paddingBottom: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))
    var paddingStart: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))
    var paddingEnd: Double? by NullableViewProperty(Validator(DC.NON_NEGATIVE))

    var backgroundColor: Long? by NullableViewProperty(Validator(LC.COLOR))

    var onClickListener by NullableViewProperty<(View) -> Unit>()
    var onLongClickListener by NullableViewProperty<(View) -> Unit>()
    var onResize by NullableViewProperty<(View) -> Unit>()

    var webExtras: HasKeys? = null
    var androidExtras: HasKeys? = null
    var iosExtras: HasKeys? = null
}