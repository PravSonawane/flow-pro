package core.lib.analytics.test.di

import core.lib.analytics.AnalyticsRepository
import core.lib.analytics.di.AnalyticsModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [AnalyticsModule::class]
)
class FakeAnalyticsModule {

    @Provides
    @Singleton
    fun analyticsRepository(): AnalyticsRepository {
        return FakeAnalyticsRepository()
    }
}
