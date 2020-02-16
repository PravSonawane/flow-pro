package core.lib.analytics.di

import core.lib.analytics.Analytics
import core.lib.analytics.impl.AnalyticsImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AnalyticsModule {

    @Provides
    @Singleton
    fun analyticsImpl(): Analytics {
        return AnalyticsImpl()
    }
}
