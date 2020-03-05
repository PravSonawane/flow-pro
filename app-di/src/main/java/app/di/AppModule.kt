package app.di

import core.lib.analytics.di.DefaultAnalyticsModule
import core.lib.plugin.impl.di.DefaultPluginModule
import dagger.Module
import data.flow.di.DefaultFlowModule

@Module(
    includes = [
        DefaultPluginModule::class,
        DefaultAnalyticsModule::class,
        DefaultFlowModule::class
    ]
)
class AppModule
