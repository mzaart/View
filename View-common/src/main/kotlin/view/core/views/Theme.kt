package view.core.views

import kotlin.reflect.KClass

/**
 * A preprocessor is a function that edits specific views before rendering.
 */
typealias PreProcessor = (Any) -> Unit

/**
 * A theme specifies the appearance of [View]s.
 *
 * A theme is just a collection of [PreProcessor]s that get applied to a [View] before it gets rendered.
 */
open class Theme {

    private val preProcessors: MutableMap<KClass<*>, PreProcessor> = mutableMapOf()

    /**
     * Adds a preprocessor to the theme.
     */
    protected fun register(klass: KClass<*>, preProcessor: PreProcessor) {
        preProcessors += klass to preProcessor
    }

    /**
     * Applies the theme to a [View].
     *
     * @param obj The object (a subtype of [View]) to apply the theme to.
     */
    fun applyTo(obj: Any) {
        val objKlass = obj::class
        preProcessors[objKlass]?.invoke(obj)
    }
}