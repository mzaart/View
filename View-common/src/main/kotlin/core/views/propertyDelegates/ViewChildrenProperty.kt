package core.views.propertyDelegates

import core.renderers.ViewTreeRenderer
import core.views.View
import core.views.layouts.Layout
import di.inject
import utils.observables.ObservableCollection
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewChildrenProperty(private val view: View): ReadOnlyProperty<Layout, MutableCollection<View>> {

    private val renderer by inject<ViewTreeRenderer>()
    private val children = ObservableCollection<View> { renderer.invalidate(view) }

    override fun getValue(thisRef: Layout, property: KProperty<*>): MutableCollection<View> = children
}