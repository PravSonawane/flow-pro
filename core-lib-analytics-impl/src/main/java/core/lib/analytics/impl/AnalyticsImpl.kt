package core.lib.analytics.impl

import core.lib.analytics.Analytics
import core.lib.analytics.firebase.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsImpl @Inject constructor(
    private val loggingAnalytics: LoggingAnalytics,
    private val firebaseAnalytics: FirebaseAnalytics
) : Analytics {

    override fun logEvent(key: String, attributes: Map<String, Any?>) {
        loggingAnalytics.logEvent(key, attributes)
        firebaseAnalytics.logEvent(key, attributes)
    }
}
