package ui.feature.flow.selectstep

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
        SelectStepViewModelModule::class
    ]
)
@FeatureScope
interface SelectStepComponent : MainComponent {
    fun injectIn(fragment: SelectStepFragment)

    /** Dagger Builder for [SelectStepComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): SelectStepComponent
    }
}
