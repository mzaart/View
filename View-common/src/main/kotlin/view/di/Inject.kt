package view.di

import org.kodein.di.Multi2
import org.kodein.di.erased.M
import org.kodein.di.erased.instance
import kotlin.reflect.KProperty


inline fun <reified T: Any> inject(tag: String? = null): DelegateProvider<T>
    = DelegateProvider { r, p -> KodeinContainer.kodein.instance<T>(tag).provideDelegate(r, p) }

inline fun <reified T: Any, reified A: Any> inject(tag: String? = null, arg: A): DelegateProvider<T>
        = DelegateProvider { r, p -> KodeinContainer.kodein.instance<A, T>(tag, arg=arg).provideDelegate(r, p) }

inline fun <reified T: Any, reified A0: Any, reified A1: Any> inject(tag: String? = null, arg0: A0, arg1: A1): DelegateProvider<T>
        = DelegateProvider { r, p -> KodeinContainer.kodein.instance<Multi2<A0, A1>, T>(tag, arg= M(arg0, arg1)).provideDelegate(r, p) }

class DelegateProvider<T: Any>(val delegateFactory: (thisRef: Any?, prop: KProperty<Any?>) -> Lazy<T>) {

    operator fun provideDelegate(
            thisRef: Any?,
            prop: KProperty<*>
    ) = delegateFactory(thisRef, prop)
}
