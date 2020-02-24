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
        StepListViewModelModule::class
    ]
)
@FeatureScope
interface StepListComponent : MainComponent {
    fun injectIn(fragment: StepListFragment)

    /** Dagger Builder for [StepListComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): StepListComponent
    }
}
