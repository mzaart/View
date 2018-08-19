package core.views.animations

import kotlin.properties.Delegates

class AnimationSet: Animation() {

    enum class SetType {
        CONCURRENT,
        SEQUENTIAL
    }

    val animations: MutableList<Animation> = mutableListOf()
    var setType: SetType by Delegates.notNull()

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