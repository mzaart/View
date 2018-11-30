package view.core.views.animations

/**
 * Defines an interface for classes that execute a function every interval of time.
 */
interface Timer {

    /**
     * Executes the passed function every interval of time indefinitely.
     *
     * @param interval The time interval's length in milliseconds
     * @param func The function to be executed.
     */
    fun repeat(interval: Int, func: (Int) -> Unit)

    /**
     * Executes the passed function every interval of time a given number of times.
     *
     * @param count Determines the number of times the function will be called
     * @param interval The time interval's length in milliseconds
     * @param func The function to be executed.
     */
    fun repeat(count: Int, interval: Int, func: (Int) -> Unit)

    /**
     * Cancels any future executions of passed functions.
     */
    fun stop()
}