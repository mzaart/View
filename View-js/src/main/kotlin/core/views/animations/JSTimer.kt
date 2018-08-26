package core.views.animations

import kotlin.browser.window

class JSTimer: Timer {

    private var intervalID: Int? = null
    private var iterationNumber: Int = 0
    private var maxIterations: Int? = null

    override fun repeat(interval: Int, func: (Int) -> Unit) {
        repeat(-1, interval, func)
    }

    override fun repeat(count: Int, interval: Int, func: (Int) -> Unit) {
        if (intervalID != null) {
            throw IllegalStateException("Timer already running")
        }
        if (count > 0) {
            maxIterations = count
        }
        intervalID = window.setInterval(wrapHandler(func), interval)
    }

    override fun stop() {
        if (intervalID == null) {
            throw IllegalStateException("Timer is not running")
        }
        window.clearInterval(intervalID!!)
    }

    private fun wrapHandler(handler: (Int) -> Unit): () -> Unit {
        val wrapper: () -> Unit = wrapper@ {
            if (maxIterations != null) {
                if (iterationNumber > maxIterations!!) {
                    window.clearInterval(intervalID!!)
                    return@wrapper
                }
                iterationNumber++
            }
            handler(iterationNumber)
        }
        return wrapper
    }
}