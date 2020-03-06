package ui.feature.flow.list

import app.di.test.TestMainComponent
import dagger.Component
import ui.lib.di.FeatureScope
import ui.lib.di.UiModule

@Component(
    dependencies = [
        TestMainComponent::class
    ],
    modules = [
        UiModule::class,
        FlowListViewModelModule::class
    ]
)
@FeatureScope
interface TestFlowListComponent : FlowListComponent, TestMainComponent {

    fun injectIn(test: FlowListViewModelTests)

    /** Dagger Builder for [TestFlowListComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: TestMainComponent): Builder
        fun build(): TestFlowListComponent
    }
}
