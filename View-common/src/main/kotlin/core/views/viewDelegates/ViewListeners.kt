package core.views.viewDelegates

import core.renderers.ViewRenderer
import core.views.View
import kotlin.reflect.KProperty

typealias Listener = (View) -> Unit
typealias ListenerSetter = (renderer: ViewRenderer, listener: Listener) -> Unit

abstract class ViewListener(
        var listener: (View) -> Unit,
        val rendererListenerSetter: ListenerSetter
): AbstractViewProperty<Listener>(listener) {

    override operator fun setValue(thisRef: View, property: KProperty<*>, value: Listener) {
        rendererListenerSetter(renderer, listener)
        listener = value
    }
}

class OnClickListener(listener: (View) -> Unit = {}): ViewListener(listener, { r, l -> r.setOnClickListener(l)})
class OnLongClickListener(listener: (View) -> Unit = {}): ViewListener(listener, { r, l -> r.setOnLongClickListener(l)})
class OnResizeListener(listener: (View) -> Unit = {}): ViewListener(listener, { r, l -> r.setOnResizeListener(l)})