package utils.extensions

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

fun <T: Any> T.properties() = this::class.members.filter { it is KProperty<*> } as List<KProperty<*>>

fun <A: Annotation> KProperty<*>.hasAnnotation(annotation: KClass<A>) = this.annotations.any { a ->
    a::class == annotation
}

fun <T: Any> T.hasProperty(prop: KProperty<*>) = this.properties().any { it == prop }