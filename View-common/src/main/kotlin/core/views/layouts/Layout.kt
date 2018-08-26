package core.views.layouts

import core.views.View
import core.views.propertyDelegates.ViewChildrenProperty

open class Layout: View() {

    protected val childViews: MutableList<View> by ViewChildrenProperty(id)

    open fun addChild(child: View): Boolean {
        child.style.extendStyle(style)
        return childViews.add(child)
    }

    fun children() = childViews.toList()
    fun getChild(index: Int) = childViews[index]
    open fun removeChild(child: View) = childViews.remove(child)
    open fun removeChild(index: Int) = childViews.removeAt(index)
}