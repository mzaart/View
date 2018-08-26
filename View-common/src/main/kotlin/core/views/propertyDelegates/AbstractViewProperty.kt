package core.views.propertyDelegates

import core.renderers.ViewRenderer
import core.views.View
import di.inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class AbstractViewProperty<T>(private var value: T): ReadWriteProperty<View, T> {

    protected val renderer by inject<ViewRenderer>()

    override fun getValue(thisRef: View, property: KProperty<*>) = value
}