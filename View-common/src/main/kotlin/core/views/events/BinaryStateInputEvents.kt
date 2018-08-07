package core.views.events

enum class BinaryStateInputEvents: Event {

    ON_STATE_CHANGED {
        override fun getName() = "ON_STATE_CHANGED"
    }
}