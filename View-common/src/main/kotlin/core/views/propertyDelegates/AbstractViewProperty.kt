package core.views.propertyDelegates

import core.renderers.ViewTreeRenderer
import core.views.View
import di.inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class AbstractViewProperty<T>(protected var value: T): ReadWriteProperty<View, T> {

    protected val renderer by inject<ViewTreeRenderer>()

    override fun getValue(thisRef: View, property: KProperty<*>) = value
}