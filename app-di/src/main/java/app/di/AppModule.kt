package app.di

import core.lib.analytics.di.DefaultAnalyticsModule
import core.lib.plugin.impl.di.DefaultPluginModule
import dagger.Module
import data.flow.di.FlowModule

@Module(
    includes = [
        DefaultPluginModule::class,
        DefaultAnalyticsModule::class,
        FlowModule::class
    ]
)
class AppModule
