package core.views.animations

import di.Inject
import kotlin.properties.Delegates

abstract class Animation {

    companion object {
        const val DEFAULT_INTERVAL = 10
    }

    protected val timer by Inject(Timer::class)

    var interval = Animation.DEFAULT_INTERVAL
    var duration: Double by Delegates.notNull()

    var onStart: (() -> Unit)? = null
    var onEnd: (() -> Unit)? = null

    abstract fun start()

    open fun stop() {
        timer.stop()
    }
}