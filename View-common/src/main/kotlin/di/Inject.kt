package di

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


class Inject<T: Any>(private val type: KClass<T>): ReadOnlyProperty<Any, T> {

    override operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        return Container.resolveDependency(type)
    }
}