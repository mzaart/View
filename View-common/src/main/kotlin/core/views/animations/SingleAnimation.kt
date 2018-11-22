package core.views.animations

import kotlin.properties.Delegates.notNull

/**
 * A single animation.
 */
open class SingleAnimation : Animation() {

    /**
     * A function that defines the rate of change of the animation.
     *
     * The function takes the time step as an input.
     */
    var interpolator: (Double) -> Double by notNull()

    /**
     * A function that modifies the view's state.
     *
     * This function takes the interpolated value of the current time step as an input.
     */
    open var applyInterpolatedValue: (Double) -> Unit by notNull()

    /**
     * A listener that gets invoked whenever the animation repeats.
     */
    var onRepeat: ((iterCount: Int) -> Unit)? = null

    /**
     * Starts the animation.
     */
    override fun start() {
        onStart?.invoke()
        val count = (duration / interval).toInt()
        timer.repeat(count, interval) { iterCount: Int ->
            val progress = interval*iterCount / duration
            val interpolatedVal = interpolator(progress)
            onRepeat?.invoke(iterCount)
            applyInterpolatedValue(interpolatedVal)

            if (iterCount == count) {
                onEnd?.invoke()
            }
        }
    }
}