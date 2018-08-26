package di

import core.loaders.Ids
import core.loaders.builders.ViewBuilder
import core.loaders.builders.display.ImageViewBuilder
import core.loaders.builders.display.TextViewBuilder
import core.loaders.builders.input.binaryStateInput.CheckBoxBuilder
import core.loaders.builders.input.binaryStateInput.RadioButtonBuilder
import core.loaders.builders.input.binaryStateInput.SwitchBuilder
import core.loaders.builders.input.textInput.EditTextBuilder
import core.loaders.builders.layouts.GridLayoutBuilder
import core.loaders.builders.layouts.LinearLayoutBuilder
import core.loaders.builders.layouts.RelativeLayoutBuilder
import core.loaders.builders.layouts.ScrollLayoutBuilder
import core.renderers.PlaceholderRenderer
import core.renderers.ViewRenderer
import core.views.display.ImageView
import core.views.display.TextView
import core.views.input.binaryStateInput.CheckBox
import core.views.input.binaryStateInput.RadioButton
import core.views.input.textInput.EditText
import core.views.layouts.GridLayout
import core.views.layouts.LinearLayout
import core.views.layouts.RelativeLayout
import core.views.layouts.ScrollLayout
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import utils.extensions.nameAsSnakeFormat

object KodeinContainer {

    val kodein = Kodein {

        bind<Ids>() with singleton { Ids() }

        bind<ViewRenderer>() with singleton { PlaceholderRenderer() }

        // view builders
        bind<ViewBuilder<*>>(LinearLayout::class.nameAsSnakeFormat()) with provider { LinearLayoutBuilder() }
        bind<ViewBuilder<*>>(RelativeLayout::class.nameAsSnakeFormat()) with provider { RelativeLayoutBuilder() }
        bind<ViewBuilder<*>>(ScrollLayout::class.nameAsSnakeFormat()) with provider { ScrollLayoutBuilder() }
        bind<ViewBuilder<*>>(GridLayout::class.nameAsSnakeFormat()) with provider { GridLayoutBuilder() }

        bind<ViewBuilder<*>>(TextView::class.nameAsSnakeFormat()) with provider { TextViewBuilder() }
        bind<ViewBuilder<*>>(ImageView::class.nameAsSnakeFormat()) with provider { ImageViewBuilder() }

        bind<ViewBuilder<*>>(EditText::class.nameAsSnakeFormat()) with provider { EditTextBuilder() }
        bind<ViewBuilder<*>>(CheckBox::class.nameAsSnakeFormat()) with provider { CheckBoxBuilder() }
        bind<ViewBuilder<*>>(RadioButton::class.nameAsSnakeFormat()) with provider { RadioButtonBuilder() }
        bind<ViewBuilder<*>>(SwitchBuilder::class.nameAsSnakeFormat()) with provider { SwitchBuilder() }
    }
}

actual inline fun <reified T : Any> inject(tag: String?): DelegateProvider<T>
        = DelegateProvider { r, p -> KodeinContainer.kodein.instance<T>(tag).provideDelegate(r, p) }