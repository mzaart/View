package di

import kotlin.reflect.KClass

object Container {

    enum class Scope {
        SINGLETON, // one instance through life cycle of app
        FACTORY // separate instance for each call
    }

    private var dependencies: MutableMap<KClass<*>, Pair<Scope, () -> Any>> = mutableMapOf()
    private var singletonStorage: MutableMap<KClass<*>, Any> = mutableMapOf()

    fun <T: Any> addDependency(superType: KClass<T>, scope: Scope, factoryFunc: () -> T) {
        if (dependencies.keys.contains(superType)) {
            throw IllegalArgumentException("Type already exists")
        }
        dependencies[superType] = Pair(scope, factoryFunc)
    }

    fun <T: Any> resolveDependency(superType: KClass<T>): T {
        val scope = dependencies.getValue(superType).first

        val dep: Any
        if (scope == Scope.SINGLETON) {
            if (!singletonStorage.keys.contains(superType)) {
                singletonStorage[superType] = dependencies.getValue(superType).second()
            }
            dep = singletonStorage[superType] ?: throw DependencyInjectionException("Dependency $superType not found")

        } else {
            val pair = dependencies.get(superType) ?:
                throw DependencyInjectionException("Dependency $superType not found")
            dep = pair.second()
        }

        if (!superType.isInstance(dep)) {
            throw DependencyInjectionException("Container not properly configured for type $superType")
        }
        return dep as T
    }

    fun clearContainer() {
        dependencies = mutableMapOf()
        singletonStorage = mutableMapOf()
    }
}