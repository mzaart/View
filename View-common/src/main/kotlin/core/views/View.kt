package core.views

import core.views.layouts.Layout
import core.views.viewDelegates.*
import utils.validators.DNonNegative
import utils.validators.Validator

abstract class View {

    enum class Visibility {
        VISIBLE,
        INVISIBLE,
        GONE
    }

    var id: Int by ViewProperty(-1)

    var width: Double by ViewProperty(-1.0)
    var height: Double by ViewProperty(-1.0)

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

    var onClickListener: (View) -> Unit by OnClickListener()
    var onLongClickListener: (View) -> Unit by OnLongClickListener()
    var onResize: (View) -> Unit by OnResizeListener()
}