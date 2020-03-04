package app.di

import core.lib.analytics.di.AnalyticsComponent
import core.lib.plugin.di.PluginComponent
import dagger.Component
import data.flow.di.FlowComponent
import javax.inject.Singleton

@Component(
    modules = [
        TestAppModule::class
    ]
)
@Singleton
interface TestAppComponent :
    PluginComponent,
    AnalyticsComponent,
    FlowComponent {
}