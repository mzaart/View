package view.utils.observables

/**
 * A collection that notifies its subscribes whenever its contents are changed.
 */
class ObservableCollection<E>(
        private val list: MutableList<E> = ArrayList(),
        private val onChangeListener: (ObservableCollection<E>) -> Unit
): MutableCollection<E> by list {


    override fun add(element: E) = boolReturningOp { list.add(element) }

    override fun remove(element: E) = boolReturningOp { list.remove(element) }

    override fun addAll(elements: Collection<E>) = boolReturningOp {
        elements.map { list.add(it) }.reduce { acc, b -> acc && b }
    }

    override fun removeAll(elements: Collection<E>) = boolReturningOp {
        elements.map { list.remove(it) }.reduce { acc, b -> acc || b }
    }

    override fun retainAll(elements: Collection<E>) = boolReturningOp lambda@ {
        var removed = false
        val iterator = iterator()
        for (e in iterator) {
            if (e !in elements) {
                iterator.remove()
                removed = true
            }
        }
        return@lambda removed
    }

    override fun clear() {
        val iterator = iterator()
        for (e in iterator) {
            iterator.remove()
        }
        onChangeListener(this)
    }

    private fun boolReturningOp(op: () -> Boolean): Boolean {
        val result = op()
        if (result) onChangeListener(this)
        return result
    }
}