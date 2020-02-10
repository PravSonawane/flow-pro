package ui.newflow.selectnode

import app.di.MainComponent
import dagger.Component
import data.flow.di.FlowModule
import ui.lib.di.FeatureScope
import ui.lib.di.UiModule

@Component(
    dependencies = [
        MainComponent::class
    ],
    modules = [
        UiModule::class,
        NewFlowSelectNodeViewModelModule::class,
        FlowModule::class
    ]
)
@FeatureScope
interface NewFlowSelectNodeComponent {
    fun injectIn(fragment: NewFlowSelectNodeFragment)

    /** Dagger Builder for [NewFlowSelectNodeComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): NewFlowSelectNodeComponent
    }
}