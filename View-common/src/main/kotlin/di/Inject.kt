package di

import kotlin.reflect.KProperty


expect inline fun <reified T: Any> inject(tag: String? = null): DelegateProvider<T>


class DelegateProvider<T: Any>(val delegateFactory: (thisRef: Any?, prop: KProperty<Any?>) -> Lazy<T>) {

    operator fun provideDelegate(
            thisRef: Any?,
            prop: KProperty<*>
    ) = delegateFactory(thisRef, prop)
}