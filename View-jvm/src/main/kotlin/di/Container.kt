package di

import kotlin.reflect.KClass

actual class Container {

    actual fun <T : Any> resolveDependency(superType: KClass<T>): T {
        TODO("not implemented")
    }
}