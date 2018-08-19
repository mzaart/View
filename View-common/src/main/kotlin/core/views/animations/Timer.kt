package core.views.animations

interface Timer {

    fun repeat(count: Int, interval: Int, func: (Int) -> Unit)
    fun stop()
}