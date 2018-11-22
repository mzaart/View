package core.views.animations

import di.inject
import kotlin.properties.Delegates

/**
 * This class is a base class for all animations.
 *
 * An animation is just a series of modifications of the view's state that get applied successively over a
 * duration of time.
 */
abstract class Animation {

    companion object {

        /**
         * The default time interval for the animation.
         */
        const val DEFAULT_INTERVAL = 10
    }

    protected val timer by inject<Timer>()

    /**
     * The time interval between the successive modifications of the view's state.
     */
    var interval = Animation.DEFAULT_INTERVAL

    /**
     * The total duration of the animation.
     *
     * A negative value makes the animation run for ever.
     */
    var duration: Double by Delegates.notNull()

    /**
     * A listener that gets invoked when the animation starts.
     */
    var onStart: (() -> Unit)? = null

    /**
     * A listener that gets invoked when the animation starts.
     */
    var onEnd: (() -> Unit)? = null

    /**
     * Starts the animation.
     */
    abstract fun start()

    /**
     * Stops the animation.
     */
    open fun stop() {
        timer.stop()
    }
}