package ui.feature.newflow.title

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
        NewFlowTitleViewModelModule::class,
        DefaultFlowModule::class
    ]
)
@FeatureScope
interface NewFlowTitleComponent : MainComponent, FlowComponent {
    fun injectIn(fragment: NewFlowTitleFragment)

    /** Dagger Builder for [NewFlowTitleComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): NewFlowTitleComponent
    }
}
