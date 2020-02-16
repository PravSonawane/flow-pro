package ui.feature.flow.steplist

import app.di.MainComponent
import dagger.Component
import ui.lib.di.FeatureScope
import ui.lib.di.UiModule

@Component(
    dependencies = [
        MainComponent::class
    ],
    modules = [
        UiModule::class,
        FlowStepListViewModelModule::class
    ]
)
@FeatureScope
interface FlowStepListComponent : MainComponent {
    fun injectIn(fragment: FlowStepListFragment)

    /** Dagger Builder for [FlowStepListComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): FlowStepListComponent
    }
}
