package app.di

import core.lib.analytics.di.AnalyticsModule
import core.lib.plugin.impl.di.DefaultPluginModule
import dagger.Module
import data.flow.di.FlowModule

@Module(
    includes = [
        DefaultPluginModule::class,
        AnalyticsModule::class,
        FlowModule::class
    ]
)
class AppModule
