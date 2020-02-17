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
        analytics.logEvent(Analytics.KEY_DEBUG, attributes)
        return stream.publish(data)
    }

    override fun subscribe(): Observable<T> {
        return stream.subscribe()
            .doOnNext {
                val attributes: Map<String, String> = mapOf(
                    "analyticsKey" to analyticsKey,
                    "onNext" to it.toString()
                )
                analytics.logEvent(Analytics.KEY_DEBUG, attributes)
            }
            .doOnSubscribe {
                val attributes: Map<String, String> = mapOf(
                    "analyticsKey" to analyticsKey,
                    "onSubscribe" to it.toString()
                )
                analytics.logEvent(Analytics.KEY_DEBUG, attributes)
            }
            .doOnComplete {
                val attributes: Map<String, String> = mapOf(
                    "analyticsKey" to analyticsKey,
                    "onComplete" to Unit.toString()
                )
                analytics.logEvent(Analytics.KEY_DEBUG, attributes)
            }
            .doOnDispose {
                val attributes: Map<String, String> = mapOf(
                    "analyticsKey" to analyticsKey,
                    "onDispose" to Unit.toString()
                )
                analytics.logEvent(Analytics.KEY_DEBUG, attributes)
            }
            .doOnError {
                val attributes: Map<String, String> = mapOf(
                    "analyticsKey" to analyticsKey,
                    "onError" to it.toString()
                )
                analytics.logEvent(Analytics.KEY_DEBUG, attributes)
            }
    }
}
