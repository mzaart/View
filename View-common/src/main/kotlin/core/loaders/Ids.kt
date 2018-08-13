package core.loaders

class Ids {

    private val ids: MutableList<Int> = mutableListOf()
    private var idCounter = 0

    fun newId(): Int {
        val newId = idCounter++
        ids += newId
        return newId
    }

    fun addId(id: Int): Boolean {
        if (containsId(id)) {
            return false
        }
        ids += id
        return true
    }

    fun containsId(id: Int) = ids.contains(id)

    fun clear() = ids.clear()
}