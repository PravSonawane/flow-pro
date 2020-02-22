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
        val attributes: Map<String, String> = mapOf(
            "analyticsKey" to analyticsKey,
            "onPublish" to data.toString()
        )
        analytics.logEvent(analyticsKey, attributes)
        return stream.publish(data)
    }

    override fun subscribe(): Observable<T> {
        return stream.subscribe()
            .doOnNext {
                val attributes: Map<String, String> = mapOf(
                    "onNext" to it.toString()
                )
                analytics.logEvent(analyticsKey, attributes)
            }
            .doOnSubscribe {
                val attributes: Map<String, String> = mapOf(
                    "onSubscribe" to it.toString()
                )
                analytics.logEvent(analyticsKey, attributes)
            }
            .doOnComplete {
                val attributes: Map<String, String> = mapOf(
                    "onComplete" to Unit.toString()
                )
                analytics.logEvent(analyticsKey, attributes)
            }
            .doOnDispose {
                val attributes: Map<String, String> = mapOf(
                    "onDispose" to Unit.toString()
                )
                analytics.logEvent(analyticsKey, attributes)
            }
            .doOnError {
                val attributes: Map<String, String> = mapOf(
                    "onError" to it.toString()
                )
                analytics.logEvent(analyticsKey, attributes)
            }
    }
}
