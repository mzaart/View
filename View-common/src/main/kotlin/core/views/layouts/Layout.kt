package core.views.layouts

import core.views.View
import core.views.viewDelegates.ViewChildrenProperty

open class Layout: View() {

    protected val children: MutableList<View> by ViewChildrenProperty(id)

    fun getChild(index: Int) = children[index]
    open fun removeChild(child: View) = children.remove(child)
    open fun removeChild(index: Int) = children.removeAt(index)
}