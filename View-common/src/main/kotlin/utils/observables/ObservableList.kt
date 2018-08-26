package utils.observables

class ObservableList<E>(private val onChangeListener: () -> Unit): MutableList<E> {

    // todo consider refactoring using class delegates

    private val list: MutableList<E> = mutableListOf()

    override val size: Int
        get() = list.size


    override fun add(element: E): Boolean {
        val added = list.add(element)
        if (added) {
            onChangeListener()
        }
        return added
    }

    override fun add(index: Int, element: E) {
        list.add(index, element)
        onChangeListener()
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        val added = list.addAll(index, elements)
        if (added) {
            onChangeListener()
        }
        return added
    }

    override fun addAll(elements: Collection<E>): Boolean {
        val added = list.addAll(elements)
        if (added) {
            onChangeListener()
        }
        return added
    }

    override fun clear() {
        list.clear()
        onChangeListener()
    }

    override fun remove(element: E): Boolean {
        val removed = list.remove(element)
        if (removed) {
            onChangeListener()
        }
        return removed
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        val removed = list.removeAll(elements)
        if (removed) {
            onChangeListener()
        }
        return removed
    }

    override fun removeAt(index: Int): E {
        val e = list.removeAt(index)
        onChangeListener()
        return e
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        val removed = list.retainAll(elements)
        if (removed) {
            onChangeListener()
        }
        return removed
    }

    override fun set(index: Int, element: E): E {
        val prev = list.set(index, element)
        onChangeListener()
        return prev
    }

    override fun contains(element: E): Boolean = list.contains(element)
    override fun containsAll(elements: Collection<E>): Boolean = list.containsAll(elements)
    override fun get(index: Int): E = list.get(index)
    override fun indexOf(element: E): Int = list.indexOf(element)
    override fun isEmpty(): Boolean = list.isEmpty()
    override fun iterator(): MutableIterator<E> = list.iterator()
    override fun lastIndexOf(element: E): Int = list.lastIndexOf(element)
    override fun listIterator(): MutableListIterator<E> = list.listIterator()
    override fun listIterator(index: Int): MutableListIterator<E> = list.listIterator(index)
    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> = list.subList(fromIndex, toIndex)
}