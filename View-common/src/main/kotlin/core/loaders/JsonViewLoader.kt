package core.loaders

import core.views.layouts.Layout

class JsonViewLoader(private val jsonString: String): ViewLoader {

    override fun loadViewTree(): Layout {


        throw RuntimeException()
    }
}