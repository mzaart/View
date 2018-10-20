package core.views

import kotlin.reflect.KClass

typealias PreProcessor = (Any) -> Unit

open class Theme {

    private val preProcessors: MutableMap<KClass<*>, PreProcessor> = mutableMapOf()

    protected fun register(klass: KClass<*>, preProcessor: PreProcessor) {
        preProcessors += klass to preProcessor
    }

    fun applyTo(obj: Any) {
        val objKlass = obj::class
        preProcessors[objKlass]?.invoke(obj)
    }
}