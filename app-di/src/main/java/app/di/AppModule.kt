package app.di

import core.lib.analytics.di.AnalyticsModule
import core.lib.plugin.impl.di.PluginModule
import dagger.Module
import data.flow.di.FlowModule

@Module(
    includes = [
        PluginModule::class,
        AnalyticsModule::class,
        FlowModule::class
    ]
)
class AppModule
