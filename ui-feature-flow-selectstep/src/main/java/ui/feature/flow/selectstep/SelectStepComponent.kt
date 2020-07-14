package ui.feature.flow.selectstep

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
        SelectStepViewModelModule::class,
        DefaultFlowModule::class
    ]
)
@FeatureScope
interface SelectStepComponent : MainComponent, FlowComponent {
    fun injectIn(fragment: SelectStepFragment)

    /** Dagger Builder for [SelectStepComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): SelectStepComponent
    }
}
