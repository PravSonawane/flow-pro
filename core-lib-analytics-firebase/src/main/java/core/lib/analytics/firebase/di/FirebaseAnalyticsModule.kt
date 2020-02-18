package core.lib.analytics.firebase.di

import android.content.Context
import app.di.annotations.ApplicationContext
import core.lib.analytics.firebase.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseAnalyticsModule {

    @Singleton
    @Provides
    fun firebaseAnalytics(@ApplicationContext context: Context): FirebaseAnalytics {
        return FirebaseAnalytics(context)
    }
}
