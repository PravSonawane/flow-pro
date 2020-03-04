package app.di

import core.lib.analytics.test.di.FakeAnalyticsModule
import core.lib.plugin.test.di.FakePluginModule
import dagger.Module
import data.flow.di.FakeFlowModule

@Module(
    includes = [
        FakePluginModule::class,
        FakeAnalyticsModule::class,
        FakeFlowModule::class
    ]
)
class TestAppModule