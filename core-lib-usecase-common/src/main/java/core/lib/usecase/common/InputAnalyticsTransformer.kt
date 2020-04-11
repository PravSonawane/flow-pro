package core.lib.usecase.common

import core.lib.analytics.AnalyticsRepository
import domain.models.core.Mappable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class InputAnalyticsTransformer<T> @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) : ObservableTransformer<InputAnalyticsTransformer.Input<T>, T> {

    override fun apply(upstream: Observable<Input<T>>): ObservableSource<T> {
        return upstream
            .doOnNext { log(it) }
            .map { it.data }
    }

    private fun log(it: Input<T>) {
        val attributes: MutableMap<String, Any?> = LinkedHashMap()
        attributes["message"] = "input"

        if (it.data is Mappable) {
            attributes.putAll(it.data.toMap())
        } else {
            attributes["data"] = it.data
        }

        analyticsRepository.logEvent(it.analyticsKey, attributes)
    }

    data class Input<T>(
        val analyticsKey: String,
        val data: T
    )
}
