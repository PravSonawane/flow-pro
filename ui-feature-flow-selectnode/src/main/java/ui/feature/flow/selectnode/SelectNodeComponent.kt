package ui.feature.flow.selectnode

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
        SelectNodeViewModelModule::class
    ]
)
@FeatureScope
interface SelectNodeComponent : MainComponent {
    fun injectIn(fragment: SelectNodeFragment)

    /** Dagger Builder for [SelectNodeComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): SelectNodeComponent
    }
}
