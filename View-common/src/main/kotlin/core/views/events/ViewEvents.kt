package core.views.events

enum class ViewEvents: Event {

    ON_CLICK {
        override fun getName() = "ON_CLICK"
    },

    ON_LONG_CLICK {
        override fun getName() = "ON_LONG_CLICK"
    },

    ON_RESIZE {
        override fun getName() = "ON_RESIZE"
    }
}