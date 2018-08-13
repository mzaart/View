package utils.extensions

fun <T> T?.nonNull(callBack: (T) -> Unit) {
    if (this != null) {
        callBack(this)
    }
}