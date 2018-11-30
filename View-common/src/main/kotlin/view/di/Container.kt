package view.di

import view.core.loaders.Ids
import view.core.loaders.builders.ViewBuilder
import view.core.loaders.builders.display.ImageViewBuilder
import view.core.loaders.builders.display.TextViewBuilder
import view.core.loaders.builders.input.ButtonBuilder
import view.core.loaders.builders.input.binaryStateInput.CheckBoxBuilder
import view.core.loaders.builders.input.binaryStateInput.RadioButtonBuilder
import view.core.loaders.builders.input.binaryStateInput.SwitchBuilder
import view.core.loaders.builders.input.textInput.EditTextBuilder
import view.core.loaders.builders.layouts.GridLayoutBuilder
import view.core.loaders.builders.layouts.LinearLayoutBuilder
import view.core.loaders.builders.layouts.RelativeLayoutBuilder
import org.kodein.di.Kodein
import org.kodein.di.conf.ConfigurableKodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton

/**
 * Contains the bindings for DI.
 */
object KodeinContainer {

    /**
     * The kodein instance used by the library.
     */
    val kodein = ConfigurableKodein()

    init {
        kodein.addConfig {
            bind<Ids>() with singleton { Ids() }

            // view builders
            bind<ViewBuilder<*>>("LinearLayout") with provider { LinearLayoutBuilder() }
            bind<ViewBuilder<*>>("RelativeLayout") with provider { RelativeLayoutBuilder() }
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

    /**
     * Adds extra bindings to the container.
     *
     * Note that this should be done before the first injection, failing to do so will result in an exception
     * being thrown.
     *
     * @param kodein The kodein instance containing the bindings
     * @param allowOverrides If true, any conflicts in binding will result in the old bindings
     * being overridden by the new bindings.
     */
    fun addConfig(kodein: Kodein, allowOverrides: Boolean = true) {
        this.kodein.addExtend(kodein, allowOverrides)
    }
}