package ui.feature.flow.list

import app.di.test.TestMainComponent
import dagger.Component
import data.feature.flow.test.di.FakeFlowModule
import domain.flow.di.FlowComponent
import ui.lib.di.FeatureScope
import ui.lib.di.UiModule

@Component(
    dependencies = [
        TestMainComponent::class
    ],
    modules = [
        UiModule::class,
        FlowListViewModelModule::class,
        FakeFlowModule::class
    ]
)
@FeatureScope
interface TestFlowListComponent : FlowListComponent, TestMainComponent, FlowComponent {

    fun injectIn(test: FlowListViewModelTests)

    /** Dagger Builder for [TestFlowListComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: TestMainComponent): Builder
        fun build(): TestFlowListComponent
    }
}
