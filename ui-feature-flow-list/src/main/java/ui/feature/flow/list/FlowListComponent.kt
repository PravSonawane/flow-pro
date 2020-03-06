package ui.feature.flow.list

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
        FlowListViewModelModule::class
    ]
)
@FeatureScope
interface FlowListComponent : MainComponent {
    fun injectIn(fragment: FlowListFragment)

    /** Dagger Builder for [FlowListComponent] */
    @Component.Builder
    interface Builder {
        fun mainComponent(component: MainComponent): Builder
        fun build(): FlowListComponent
    }
}
