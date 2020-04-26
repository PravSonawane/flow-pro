package app.di

import core.lib.analytics.di.DefaultAnalyticsModule
import core.lib.cache.impl.di.DefaultCacheModule
import core.lib.experimentation.impl.di.DefaultExperimentationModule
import core.lib.plugin.impl.di.DefaultPluginModule
import dagger.Module
import data.flow.di.DefaultFlowModule

@Module(
    includes = [
        DefaultCacheModule::class,
        DefaultExperimentationModule::class,
        DefaultPluginModule::class,
        DefaultAnalyticsModule::class,
        DefaultFlowModule::class
    ]
)
class AppModule
