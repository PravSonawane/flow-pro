package core.lib.analytics.impl

import core.lib.analytics.Analytics
import core.lib.analytics.AnalyticsRepository
import core.lib.analytics.firebase.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsImpl @Inject constructor(
    private val loggingAnalytics: LoggingAnalytics,
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsRepository {

    override fun logEvent(key: String, attributes: Map<String, Any?>, priority: Analytics.Priority) {
        loggingAnalytics.logEvent(key, attributes)
        firebaseAnalytics.logEvent(key, attributes)
    }
}
