package ui.feature.flow.stepdetails

import app.di.MainComponent
import dagger.Component
import data.flow.di.DefaultFlowModule
import domain.flow.di.FlowComponent
import ui.lib.di.FeatureScope
import ui.lib.di.UiModule

@Component(
    dependencies = [
        MainComponent::class
    ],
    modules = [
        UiModule::class,
        StepDetailsViewModelModule::class,
        DefaultFlowModule::class
    ]
)
@FeatureScope
interface StepDetailsComponent : MainComponent, FlowComponent {
    fun injectIn(fragment: StepDetailsFragment)

    /** Dagger Builder for [StepDetailsComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): StepDetailsComponent
    }
}
