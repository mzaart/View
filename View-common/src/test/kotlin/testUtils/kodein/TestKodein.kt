package testUtils.kodein

import core.renderers.PlaceholderTreeRenderer
import core.renderers.ViewTreeRenderer
import di.KodeinContainer
import di.KodeinContainer.kodein
import org.kodein.di.Kodein
import org.kodein.di.conf.ConfigurableKodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton
import kotlin.properties.Delegates


object TestKodein {

    private var productionKodein: Kodein by Delegates.notNull()

    private val testDIOverridesKodein = Kodein {
        bind<ViewTreeRenderer>() with singleton { PlaceholderTreeRenderer() }
    }

    init {
        kodein.mutable = true

        val ck = ConfigurableKodein()
        ck.addExtend(kodein)
        productionKodein = ck.getOrConstruct()

        kodein.addExtend(testDIOverridesKodein, true)
    }

    fun addConfig(kodein: Kodein) {
        KodeinContainer.kodein.addExtend(kodein, true)
    }

    fun reset() {
        kodein.clear()
        kodein.addExtend(productionKodein)
        kodein.addExtend(testDIOverridesKodein, true)
    }
}