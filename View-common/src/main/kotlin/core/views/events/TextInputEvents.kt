package core.views.events

enum class TextInputEvents: Event {

    ON_TEXT_CHANGED {
        override fun getName() = "ON_TEXT_CHANGED"
    }
}