package app.di.test

import core.lib.analytics.test.di.FakeAnalyticsModule
import core.lib.plugin.test.di.FakePluginModule
import dagger.Module
import data.feature.flow.test.di.FakeFlowModule

@Module(
    includes = [
        FakePluginModule::class,
        FakeAnalyticsModule::class,
        FakeFlowModule::class
    ]
)
class TestAppModule