package app.di

import core.lib.analytics.di.AnalyticsModule
import dagger.Module
import data.flow.di.FlowModule

@Module(
    includes = [
        AnalyticsModule::class,
        FlowModule::class
    ]
)
class AppModule
