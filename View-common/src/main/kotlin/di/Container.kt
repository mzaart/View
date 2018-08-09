package di

import kotlin.reflect.KClass

expect class Container() {

    fun <T: Any> resolveDependency(superType: KClass<T>): T
}