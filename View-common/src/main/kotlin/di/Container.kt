package di

import core.loaders.Ids
import core.loaders.builders.ViewBuilder
import core.loaders.builders.display.ImageViewBuilder
import core.loaders.builders.display.TextViewBuilder
import core.loaders.builders.input.ButtonBuilder
import core.loaders.builders.input.binaryStateInput.CheckBoxBuilder
import core.loaders.builders.input.binaryStateInput.RadioButtonBuilder
import core.loaders.builders.input.binaryStateInput.SwitchBuilder
import core.loaders.builders.input.textInput.EditTextBuilder
import core.loaders.builders.layouts.GridLayoutBuilder
import core.loaders.builders.layouts.LinearLayoutBuilder
import core.loaders.builders.layouts.RelativeLayoutBuilder
import core.loaders.builders.layouts.ScrollLayoutBuilder
import core.renderers.PlaceholderTreeRenderer
import core.renderers.ViewTreeRenderer
import org.kodein.di.Kodein
import org.kodein.di.conf.ConfigurableKodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton

object KodeinContainer {

    val kodein = ConfigurableKodein()

    init {
        kodein.addConfig {
            bind<Ids>() with singleton { Ids() }

            bind<ViewTreeRenderer>() with singleton { PlaceholderTreeRenderer() }

            // view builders
            bind<ViewBuilder<*>>("LinearLayout") with provider { LinearLayoutBuilder() }
            bind<ViewBuilder<*>>("RelativeLayout") with provider { RelativeLayoutBuilder() }
            bind<ViewBuilder<*>>("ScrollLayout") with provider { ScrollLayoutBuilder() }
            bind<ViewBuilder<*>>("GridLayout") with provider { GridLayoutBuilder() }

            bind<ViewBuilder<*>>("TextView") with provider { TextViewBuilder() }
            bind<ViewBuilder<*>>("ImageView") with provider { ImageViewBuilder() }

            bind<ViewBuilder<*>>("Button") with provider { ButtonBuilder() }

            bind<ViewBuilder<*>>("EditText") with provider { EditTextBuilder() }
            bind<ViewBuilder<*>>("CheckBox") with provider { CheckBoxBuilder() }
            bind<ViewBuilder<*>>("RadioButton") with provider { RadioButtonBuilder() }
            bind<ViewBuilder<*>>("SwitchBuilder") with provider { SwitchBuilder() }
        }
    }

    fun addConfig(kodein: Kodein, allowOverrides: Boolean = true) {
        this.kodein.addExtend(kodein, allowOverrides)
    }
}