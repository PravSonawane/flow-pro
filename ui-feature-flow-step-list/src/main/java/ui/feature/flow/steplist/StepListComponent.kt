package ui.feature.flow.steplist

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
        StepListViewModelModule::class,
        DefaultFlowModule::class
    ]
)
@FeatureScope
interface StepListComponent : MainComponent, FlowComponent {
    fun injectIn(fragment: StepListFragment)

    /** Dagger Builder for [StepListComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): StepListComponent
    }
}
