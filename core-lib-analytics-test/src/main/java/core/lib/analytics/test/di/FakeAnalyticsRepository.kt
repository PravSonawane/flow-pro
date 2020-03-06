package core.lib.analytics.test.di

import core.lib.analytics.Analytics
import core.lib.analytics.AnalyticsRepository
import javax.inject.Inject

class FakeAnalyticsRepository @Inject constructor() : AnalyticsRepository {

    override fun logEvent(key: String, attributes: Map<String, Any?>, priority: Analytics.Priority) {
        println("analyticsKey: $key | logs:$attributes")
    }
}
