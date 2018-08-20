package core.loaders.viewTree

import core.views.layouts.Layout

interface ViewTreeLoader {

    fun loadViewTree(): Layout
}