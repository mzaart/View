package core.views.animations

interface Timer {

    fun repeat(interval: Int, func: (Int) -> Unit)
    fun repeat(count: Int, interval: Int, func: (Int) -> Unit)
    fun stop()
}