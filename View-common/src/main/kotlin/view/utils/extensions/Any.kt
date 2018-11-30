package view.utils.extensions

/**
 * Executes the callback function if the object is not null.
 *
 * @param callBack The function to call
 */
fun <T> T?.nonNull(callBack: (T) -> Unit) {
    if (this != null) {
        callBack(this)
    }
}