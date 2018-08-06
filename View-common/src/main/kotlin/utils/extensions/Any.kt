package utils.extensions

fun Any?.notNull(f: ()-> Unit){
    if (this != null){
        f()
    }
}

inline fun <T: Any> notNull(value: T?, callback: (t: T) -> Unit) {
    if (value != null) {
        callback(value)
    }
}
