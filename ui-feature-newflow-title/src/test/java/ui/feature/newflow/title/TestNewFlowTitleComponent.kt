package ui.feature.newflow.title

import app.di.TestMainComponent
import dagger.Component
import ui.lib.di.FeatureScope
import ui.lib.di.UiModule

@Component(
    dependencies = [
        TestMainComponent::class
    ],
    modules = [
        UiModule::class,
        NewFlowTitleViewModelModule::class
    ]
)
@FeatureScope
interface TestNewFlowTitleComponent : TestMainComponent {

    /** Dagger Builder for [TestNewFlowTitleComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: TestMainComponent): Builder
        fun build(): TestNewFlowTitleComponent
    }
}
