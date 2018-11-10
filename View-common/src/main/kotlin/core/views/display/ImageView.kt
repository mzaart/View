package core.views.display

import core.views.View
import core.views.propertyDelegates.LateInitVal

class ImageView: View() {

    enum class ScaleType {

        CENTER,
        CENTER_CROP,
        CENTER_INSIDE,
        FIT
    }

    var imageResource: String by LateInitVal()
    var scaleType: ScaleType by LateInitVal()
}