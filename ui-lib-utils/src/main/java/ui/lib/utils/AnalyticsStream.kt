package ui.lib.utils

import core.lib.analytics.Analytics
import io.reactivex.Observable
import javax.inject.Inject

class AnalyticsStream<T> @Inject constructor(
    private val analyticsKey: String,
    private val analytics: Analytics,
    private val stream: Stream<T>
) : Stream<T> {

    override fun publish(data: T) {
        analytics.logEvent(analyticsKey, mapOf("onPublish" to data.toString()))
        return stream.publish(data)
    }

    override fun subscribe(): Observable<T> {
        return stream.subscribe()
            .doOnNext { analytics.logEvent(analyticsKey, mapOf("onNext" to it.toString())) }
            .doOnSubscribe { analytics.logEvent(analyticsKey, mapOf("onSubscribe" to it.toString())) }
            .doOnComplete { analytics.logEvent(analyticsKey, mapOf("onComplete" to Unit)) }
            .doOnDispose { analytics.logEvent(analyticsKey, mapOf("onDispose" to Unit)) }
            .doOnError { analytics.logEvent(analyticsKey, mapOf("onError" to it.toString())) }
    }
}
