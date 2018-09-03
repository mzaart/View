package core.loaders.builders

import core.loaders.Ids
import core.loaders.viewTree.IllegalViewTreeException
import core.loaders.keys.ViewKeys
import core.loaders.keys.delegates.nullable.*
import core.views.View
import di.inject
import utils.extensions.nonNull
import utils.extensions.toID

abstract class ViewBuilder<V: View>: ViewKeys() {

    protected val ids by inject<Ids>()

    var id by StringKey

    var width by DoubleKey
    var height by DoubleKey

    var visibility by EnumKey(View.Visibility.values())
    var disabled by BoolKey
    var isCard by BoolKey

    var marginTop by DoubleKey
    var marginBottom by DoubleKey
    var marginStart by DoubleKey
    var marginEnd by DoubleKey
    var marginHorizontal by DoubleKey
    var marginVertical by DoubleKey

    var paddingTop by DoubleKey
    var paddingBottom by DoubleKey
    var paddingStart by DoubleKey
    var paddingEnd by DoubleKey
    var paddingHorizontal by DoubleKey
    var paddingVertical by DoubleKey

    // style attrs
    var backgroundColor by ColorKey
    var hasShadow by BoolKey

    open val conflictingKeys = setOf(
            setOf("marginVertical", "marginTop", "marginBottom"),
            setOf("marginHorizontal", "marginStart","marginEnd"),
            setOf("paddingVertical", "paddingTop", "paddingBottom"),
            setOf("paddingHorizontal", "paddingStart","paddingEnd")
    )
    
    protected abstract val view: V

    fun applyKeys(keys: Map<String, String>): ViewBuilder<V> {
        this.keys = keys.toMutableMap()
        return this
    }

    fun build(): V {
        checkConflicts()
        applyViewAttrs()
        beforeProduction()
        return view
    }

    private fun checkConflicts() {
        for (key in keys.keys) {
            val conflictGrps = conflictingKeys.filter { it.contains(key) }
            for (grp in conflictGrps) {
                val conflictingKey = grp.firstOrNull { it != key && it in keys.keys }
                if (conflictingKey != null) {
                    throw IllegalViewTreeException("Key $key conflicts with key $conflictingKey")
                }
            }
        }
    }

    private fun applyViewAttrs() {
        val id = this.id?.toID()
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

        backgroundColor.nonNull { view.style.backgroundColor = it }
        hasShadow.nonNull { view.style.hasShadow = it }
    }

    protected open fun beforeProduction() {}
}