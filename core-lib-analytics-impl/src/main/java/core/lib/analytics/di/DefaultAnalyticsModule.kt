package core.lib.analytics.di

import core.lib.analytics.AnalyticsRepository
import core.lib.analytics.firebase.FirebaseAnalytics
import core.lib.analytics.firebase.di.FirebaseAnalyticsModule
import core.lib.analytics.impl.AnalyticsImpl
import core.lib.analytics.impl.LoggingAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [AnalyticsModule::class, FirebaseAnalyticsModule::class]
)
class DefaultAnalyticsModule {

    @Provides
    @Singleton
    fun analyticsRepository(
        loggingAnalytics: LoggingAnalytics,
        firebaseAnalytics: FirebaseAnalytics
    ): AnalyticsRepository {
        return AnalyticsImpl(loggingAnalytics, firebaseAnalytics)
    }
}
