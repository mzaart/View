package core.views.propertyDelegates

import core.renderers.ViewRenderer
import core.views.View
import core.views.layouts.Layout
import di.inject
import utils.observables.ObservableList
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewChildrenProperty(private val viewId: Int): ReadOnlyProperty<Layout, MutableList<View>> {

    private val renderer by inject<ViewRenderer>()
    private val children = ObservableList<View> { renderer.invalidate(viewId) }

    override fun getValue(thisRef: Layout, property: KProperty<*>): MutableList<View> = children
}