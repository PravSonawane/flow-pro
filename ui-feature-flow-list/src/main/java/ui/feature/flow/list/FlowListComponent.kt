package ui.feature.flow.list

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
        FlowListViewModelModule::class,
        DefaultFlowModule::class
    ]
)
@FeatureScope
interface FlowListComponent : MainComponent, FlowComponent {
    fun injectIn(fragment: FlowListFragment)

    /** Dagger Builder for [FlowListComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): FlowListComponent
    }
}
