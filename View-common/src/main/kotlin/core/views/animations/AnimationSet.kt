package core.views.animations

import kotlin.properties.Delegates

/**
 * Represents a group of animations to played.
 */
class AnimationSet: Animation() {

    /**
     * Represents the kind of the animation set.
     */
    enum class SetType {

        /**
         * The animations are played concurrently. They all start at the same time.
         */
        CONCURRENT,

        /**
         * The animations are played sequentially. The i-th animation only starts when the (i-1)-th animation ends.
         */
        SEQUENTIAL
    }

    /**
     * The list of animations to play.
     */
    val animations: MutableList<Animation> = mutableListOf()

    /**
     * Determines the animation set type.
     */
    var setType: SetType by Delegates.notNull()

    /**
     * Starts the animation.
     */
    override fun start() {
        onStart?.invoke()
        when (setType) {
            SetType.CONCURRENT -> {
                val longestAnim = animations.maxBy { it.duration }
                longestAnim?.onEnd = {
                    longestAnim?.onEnd?.invoke()
                    onEnd?.invoke()
                }
                for (anim in animations) {
                    anim.start()
                }
            }

            SetType.SEQUENTIAL -> {
                when (animations.size) {
                    0 -> return
                    1 -> animations[0].start()
                    else -> {
                        for (i in 0 until animations.size-2) {
                            animations[i].onEnd = {
                                animations[i].onEnd?.invoke()
                                animations[i+1].start()
                            }
                        }
                        val finalAnim = animations[animations.size-1]
                        finalAnim.onEnd = {
                            finalAnim.onEnd?.invoke()
                            onEnd?.invoke()
                        }
                    }
                }
            }
        }
    }
}