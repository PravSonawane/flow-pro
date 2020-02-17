package ui.lib.utils

import core.lib.analytics.Analytics
import core.lib.analytics.impl.AnalyticsImpl
import javax.inject.Inject

class StreamFactory @Inject constructor(
    private val analytics: Analytics
) {
    fun <T> simpleStream(): SimpleStream<T> {
        return SimpleStream()
    }

    fun <T> analyticsStream(
        analyticsKey: String
    ): AnalyticsStream<T> {
        return AnalyticsStream(analyticsKey, analytics, simpleStream())
    }
}
