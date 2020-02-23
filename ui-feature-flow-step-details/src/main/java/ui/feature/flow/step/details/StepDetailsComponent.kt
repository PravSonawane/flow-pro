package ui.feature.flow.step.details

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
        StepDetailsViewModelModule::class
    ]
)
@FeatureScope
interface StepDetailsComponent : MainComponent {
    fun injectIn(fragment: StepDetailsFragment)

    /** Dagger Builder for [StepDetailsComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): StepDetailsComponent
    }
}
