package ui.feature.newflow.step

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
        CreateStepViewModelModule::class,
        DefaultFlowModule::class
    ]
)
@FeatureScope
interface CreateStepComponent : MainComponent, FlowComponent {
    fun injectIn(fragment: CreateStepFragment)

    /** Dagger Builder for [CreateStepComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): CreateStepComponent
    }
}
