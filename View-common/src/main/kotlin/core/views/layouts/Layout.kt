package core.views.layouts

import core.views.View
import core.views.propertyDelegates.ViewChildrenProperty

open class Layout: View() {

    protected val children: MutableList<View> by ViewChildrenProperty(id)

    open fun addChild(child: View): Boolean {
        child.style.extendStyle(style)
        return children.add(child)
    }

    fun children() = children.toList()
    fun getChild(index: Int) = children[index]
    open fun removeChild(child: View) = children.remove(child)
    open fun removeChild(index: Int) = children.removeAt(index)
}