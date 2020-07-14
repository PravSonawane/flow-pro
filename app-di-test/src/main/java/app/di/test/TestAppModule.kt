package app.di.test

import core.lib.analytics.test.di.FakeAnalyticsModule
import core.lib.cache.test.di.FakeCacheModule
import core.lib.experimentation.test.di.FakeExperimentationModule
import core.lib.plugin.test.di.FakePluginModule
import dagger.Module

@Module(
    includes = [
        FakeCacheModule::class,
        FakeExperimentationModule::class,
        FakePluginModule::class,
        FakeAnalyticsModule::class
    ]
)
class TestAppModule
