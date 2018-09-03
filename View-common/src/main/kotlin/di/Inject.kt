package di

import org.kodein.di.erased.instance
import kotlin.reflect.KProperty


inline fun <reified T: Any> inject(tag: String? = null): DelegateProvider<T>
    = DelegateProvider { r, p -> KodeinContainer.kodein.instance<T>(tag).provideDelegate(r, p) }


class DelegateProvider<T: Any>(val delegateFactory: (thisRef: Any?, prop: KProperty<Any?>) -> Lazy<T>) {

    operator fun provideDelegate(
            thisRef: Any?,
            prop: KProperty<*>
    ) = delegateFactory(thisRef, prop)
}
