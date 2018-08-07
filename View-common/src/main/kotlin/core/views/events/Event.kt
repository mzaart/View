package core.views.events

import core.views.View

typealias EventListener = (View) -> Unit

interface Event {

    fun getName(): String
}