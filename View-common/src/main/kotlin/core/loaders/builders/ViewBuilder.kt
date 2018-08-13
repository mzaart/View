package core.loaders.builders

import core.loaders.Ids
import core.loaders.IllegalViewTreeException
import core.loaders.keys.ViewKeys
import core.loaders.keys.delegates.nullable.*
import core.views.View
import di.Inject
import utils.extensions.nonNull
import kotlin.reflect.KProperty

abstract class ViewBuilder<V: View>: ViewKeys() {

    protected val ids by Inject(Ids::class)

    private val id by IdKey()

    private val width by DoubleKey()
    private val height by DoubleKey()

    private val visibility by EnumKey<View.Visibility>()
    private val disabled by BoolKey()
    val isCard by BoolKey()

    private val marginTop by DoubleKey()
    private val marginBottom by DoubleKey()
    private val marginStart by DoubleKey()
    private val marginEnd by DoubleKey()
    private val marginHorizontal by DoubleKey()
    private val marginVertical by DoubleKey()

    private val paddingTop by DoubleKey()
    private val paddingBottom by DoubleKey()
    private val paddingStart by DoubleKey()
    private val paddingEnd by DoubleKey()
    private val paddingHorizontal by DoubleKey()
    private val paddingVertical by DoubleKey()

    override val conflictingKeys: Set<Set<KProperty<*>>> = setOf(
            setOf(::marginTop, ::marginVertical),
            setOf(::marginBottom, ::marginVertical),
            setOf(::marginStart, ::marginHorizontal),
            setOf(::marginEnd, ::marginHorizontal),
            setOf(::paddingTop, ::paddingVertical),
            setOf(::paddingBottom, ::paddingVertical),
            setOf(::paddingStart, ::paddingHorizontal),
            setOf(::paddingEnd, ::paddingHorizontal)
    )

    protected abstract val view: V

    fun applyKeys(keys: Map<String, String>): ViewBuilder<V> {
        this.keys = keys
        return this
    }

    open fun build(): V {
        applyCommonViewAttrs()
        applyAttributes()
        return view
    }

    open fun applyAttributes() {}

    private fun applyCommonViewAttrs() {
        val id = this.id
        if (id == null) {
            view.id = ids.newId()
        } else {
            view.id = if (!ids.containsId(id)) id else throw IllegalViewTreeException("ID already exists")
        }

        visibility.nonNull { view.visibility = it }
        disabled.nonNull { view.disabled = it }
        isCard.nonNull { view.isCard = it }

        val vMargin = marginVertical
        if (vMargin != null) {
            view.marginTop = vMargin
            view.marginBottom = vMargin
        } else {
            marginTop.nonNull { view.marginTop = it }
            marginBottom.nonNull { view.marginBottom = it }
        }

        val hMargin = marginHorizontal
        if (hMargin != null) {
            view.marginStart = hMargin
            view.marginEnd = hMargin
        } else {
            marginStart.nonNull { view.marginStart = it }
            marginEnd.nonNull { view.marginEnd = it }
        }

        val vPadding = paddingVertical
        if (vPadding != null) {
            view.paddingTop = vPadding
            view.paddingBottom = vPadding
        } else {
            paddingTop.nonNull { view.paddingTop = it }
            paddingBottom.nonNull { view.paddingBottom = it }
        }

        val hPadding = paddingHorizontal
        if (hPadding != null) {
            view.paddingStart = hPadding
            view.paddingEnd = hPadding
        } else {
            paddingStart.nonNull { view.paddingStart = it }
            paddingEnd.nonNull { view.paddingEnd = it }
        }
    }
}