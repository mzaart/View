package core.loaders.builders

import core.loaders.Ids
import core.loaders.viewTree.IllegalViewTreeException
import core.views.Dimension
import utils.mapBased.keys.delegates.nullable.*
import core.views.View
import di.inject
import utils.extensions.nonNull
import utils.extensions.representations
import utils.extensions.toID
import utils.mapBased.keys.HasKeys
import utils.mapBased.keys.delegates.readOnly.casts.CastingKey

/**
 * Base class for all view builders.
 *
 * This class is responsible for building the common properties of view. In other words, the base builder
 * will set all the properties in the [View] class. It is the responsibility of view specific builders to
 * set the properties of specific views.
 */
abstract class AbstractViewBuilder<V: View>: ViewBuilder<V>, ViewKeys() {

    protected val ids by inject<Ids>()

    var id by StringRWKey

    var width by StringRWKey
    var height by StringRWKey

    var visibility by EnumRWKey(View.Visibility.values())
    var disabled by BoolRWKey

    var marginTop by DoubleRWKey
    var marginBottom by DoubleRWKey
    var marginStart by DoubleRWKey
    var marginEnd by DoubleRWKey
    var marginHorizontal by DoubleRWKey
    var marginVertical by DoubleRWKey

    var paddingTop by DoubleRWKey
    var paddingBottom by DoubleRWKey
    var paddingStart by DoubleRWKey
    var paddingEnd by DoubleRWKey
    var paddingHorizontal by DoubleRWKey
    var paddingVertical by DoubleRWKey

    var webExtras by CastingKey<Map<String, Any>>()
    var androidExtras by CastingKey<Map<String, Any>>()
    var iosExtras by CastingKey<Map<String, Any>>()

    // style attrs
    var backgroundColor by ColorRWKey
    var hasShadow by BoolRWKey

    /**
     *  Contains sets of conflicting keys.
     *
     *  If more than one key belonging to the same set are present, an [IllegalViewTreeException] will be thrown.
     */
    open val conflictingKeys = setOf(
            setOf("marginVertical", "marginTop", "marginBottom"),
            setOf("marginHorizontal", "marginStart","marginEnd"),
            setOf("paddingVertical", "paddingTop", "paddingBottom"),
            setOf("paddingHorizontal", "paddingStart", "paddingEnd")
    )

    /**
     * The view instance that will have its properties set.
     */
    protected abstract val view: V

    override fun applyKeys(keys: Map<String, Any>): ViewBuilder<V> {
        this.keys = keys.toMutableMap()
        return this
    }

    /**
     * Builds the view from the keys.
     *
     * The process for building the view goes as follows:
     *      1. The keys are checked for conflicts
     *      2. Common view properties are set
     *      3. The function [beforeProduction] is called. Which can be overridden by subclasses to set
     *      view-specific properties.
     *      4. The resultant view is returned
     */
    override fun build(): V {
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
                    throw IllegalViewTreeException("NullableRWKey $key conflicts with key $conflictingKey")
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
        ids.addId(view.id);

        applyDisplay()

        applyWidth()
        applyHeight()

        applyMargins()
        applyPadding()

        applyBgColor()
        applyExtras()
    }

    protected open fun beforeProduction() {}

    private fun applyDisplay() {
        visibility.nonNull { view.visibility = it }
        disabled.nonNull { view.disabled = it }
    }

    private fun applyWidth() {
        width.nonNull {
            view.width = if (it in Dimension.Type.WRAP_CONTENT.representations()) {
                Dimension.value(Dimension.Type.WRAP_CONTENT)
            } else {
                it.toDouble()
            }
        }
    }

    private fun applyHeight() {
        height.nonNull {
            view.height = if (it in Dimension.Type.WRAP_CONTENT.representations()) {
                Dimension.value(Dimension.Type.WRAP_CONTENT)
            } else {
                it.toDouble()
            }
        }
    }

    private fun applyMargins() {
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
    }

    private fun applyPadding() {
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

    private fun applyBgColor() {
        backgroundColor.nonNull { view.backgroundColor = it }
    }

    private fun applyExtras() {
        webExtras.nonNull { view.webExtras = HasKeys(it) }
        androidExtras.nonNull { view.androidExtras = HasKeys(it) }
        iosExtras.nonNull { view.iosExtras = HasKeys(it) }
    }
}