package core.views.animations

import kotlin.properties.Delegates.notNull

open class SingleAnimation : Animation() {

    var interpolator: (Double) -> Double by notNull()
    open var applyInterpolatedValue: (Double) -> Unit by notNull()

    var onRepeat: ((iterCount: Int) -> Unit)? = null

    override fun start() {
        onStart?.invoke()
        val count = (duration / interval).toInt()
        timer.repeat(count, interval) { iterCount ->
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