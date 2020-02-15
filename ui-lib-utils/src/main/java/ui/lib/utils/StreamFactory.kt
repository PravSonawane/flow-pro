package ui.lib.utils

import core.lib.analytics.Analytics
import core.lib.analytics.impl.AnalyticsImpl
import javax.inject.Inject

class StreamFactory @Inject constructor() {
    fun <T> simpleStream(): SimpleStream<T> {
        return SimpleStream()
    }

    fun <T> analyticsStream(
        analyticsKey: String,
        analytics: Analytics = AnalyticsImpl(),
        stream: Stream<T> = SimpleStream()
    ): AnalyticsStream<T> {
        return AnalyticsStream(analyticsKey, analytics, stream)
    }
}
