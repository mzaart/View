package core.views.propertyDelegates

import core.renderers.ViewRenderer
import core.views.HasId
import di.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class AbstractViewProperty<T>(private var value: T): ReadWriteProperty<HasId, T> {

    protected val renderer by Inject(ViewRenderer::class)

    override fun getValue(thisRef: HasId, property: KProperty<*>) = value
}