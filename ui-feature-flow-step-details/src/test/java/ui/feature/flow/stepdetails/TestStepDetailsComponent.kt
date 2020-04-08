package ui.feature.flow.stepdetails

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
        StepDetailsViewModelModule::class
    ]
)
@FeatureScope
interface TestStepDetailsComponent : StepDetailsComponent, TestMainComponent {

    fun injectIn(test: StepDetailsViewModelTests)

    /** Dagger Builder for [TestStepDetailsComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: TestMainComponent): Builder
        fun build(): TestStepDetailsComponent
    }
}
