package utils.observables

class ObservableMap<K, V>(
        private val map: MutableMap<K, V> = LinkedHashMap()
): MutableMap<K, V> by map {

    interface OnMapChangedListener<K, V> {
        fun onAdd(value: Map.Entry<K, V>)
    }

    private val subscribers: MutableList<OnMapChangedListener<K, V>> = mutableListOf()

    fun subscribe(subscriber: OnMapChangedListener<K, V>) {
        subscribers += subscriber
    }

    fun unSubscribe(subscriber: OnMapChangedListener<K, V>) {
        if (subscriber !in subscribers) {
            throw IllegalArgumentException("Listener is not subscribed")
        }
        subscribers -= subscriber
    }


}