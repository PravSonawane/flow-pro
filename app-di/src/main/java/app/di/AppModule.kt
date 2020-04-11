package app.di

import core.lib.analytics.di.DefaultAnalyticsModule
import core.lib.experimentation.impl.di.DefaultExperimentationModule
import core.lib.plugin.impl.di.DefaultPluginModule
import dagger.Module
import data.flow.di.DefaultFlowModule

@Module(
    includes = [
        DefaultExperimentationModule::class,
        DefaultPluginModule::class,
        DefaultAnalyticsModule::class,
        DefaultFlowModule::class
    ]
)
class AppModule
