package core.loaders

import core.views.layouts.Layout

interface ViewLoader {

    fun loadViewTree(): Layout
}