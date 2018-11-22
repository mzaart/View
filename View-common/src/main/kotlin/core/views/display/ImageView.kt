package core.views.display

import core.views.View
import core.views.propertyDelegates.LateInitVal

/**
 * This view displays an image on the UI.
 */
class ImageView: View() {

    /**
     * Represents the scaling strategy of the image in the view.
     */
    enum class ScaleType {

        /**
         * Centers the image in the view without scaling.
         */
        CENTER,

        /**
         * Scales the image while maintaining the aspect ratio until the image is equal in size or bigger than
         * the view.
         */
        CENTER_CROP,

        /**
         * Scales the image while maintaining the aspect ratio until the image is equal in size or smaller than
         * the view.
         */
        CENTER_INSIDE,

        /**
         * Scales the image to the view's dimensions without respecting its aspect ratio.
         */
        FIT
    }

    /**
     * Represents an image resource identifier.
     */
    var imageResource: String by LateInitVal()

    /**
     * Specifies the image's scale type.
     */
    var scaleType: ScaleType by LateInitVal()
}