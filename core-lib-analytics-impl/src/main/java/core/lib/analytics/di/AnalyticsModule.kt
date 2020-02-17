package core.lib.analytics.di

import core.lib.analytics.Analytics
import core.lib.analytics.firebase.FirebaseAnalytics
import core.lib.analytics.firebase.di.FirebaseAnalyticsModule
import core.lib.analytics.impl.AnalyticsImpl
import core.lib.analytics.impl.LoggingAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [FirebaseAnalyticsModule::class]
)
class AnalyticsModule {

    @Provides
    @Singleton
    fun analytics(
        loggingAnalytics: LoggingAnalytics,
        firebaseAnalytics: FirebaseAnalytics
    ): Analytics {
        return AnalyticsImpl(loggingAnalytics, firebaseAnalytics)
    }
}
