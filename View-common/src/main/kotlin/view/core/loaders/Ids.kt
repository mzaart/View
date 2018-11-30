package view.core.loaders

/**
 * This class keeps track of used view Ids.
 */
class Ids {

    private val ids: MutableList<Int> = mutableListOf()
    private var idCounter = 0

    /**
     * Returns a new unique id
     *
     * @return The generated Id
     */
    fun newId(): Int {
        val newId = idCounter++
        ids += newId
        return newId
    }

    /**
     * Marks the id as "used"
     *
     * @param id The used Id
     */
    fun addId(id: Int): Boolean {
        if (containsId(id)) {
            return false
        }
        ids += id
        return true
    }

    /**
     * Checks if the passed id is already used.
     *
     * @param The id to check.
     * @return True if the id is used before, false otherwise
     */
    fun containsId(id: Int) = ids.contains(id)

    /**
     * Mark all previously used Ids as "unused"
     */
    fun clear() = ids.clear()
}