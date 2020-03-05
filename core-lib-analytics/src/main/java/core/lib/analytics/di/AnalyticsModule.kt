package core.lib.analytics.di

import core.lib.analytics.Analytics
import core.lib.analytics.AnalyticsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AnalyticsModule {

    @Provides
    @Singleton
    fun analytics(analyticsRepository: AnalyticsRepository): Analytics {
        return analyticsRepository
    }
}
