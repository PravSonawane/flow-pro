package ui.newflow.createstep

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
        CreateStepViewModelModule::class
    ]
)
@FeatureScope
interface CreateStepComponent : MainComponent {
    fun injectIn(fragment: CreateStepFragment)

    /** Dagger Builder for [CreateStepComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): CreateStepComponent
    }
}