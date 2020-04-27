package core.lib.usecase.common

import core.lib.analytics.AnalyticsRepository
import core.lib.experimentation.ExperimentationRepository
import core.lib.result.toDataObservable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class ExperimentationTransformer<T> @Inject constructor(
    private val analyticsRepository: AnalyticsRepository,
    private val experimentationRepository: ExperimentationRepository
) : ObservableTransformer<ExperimentationTransformer.Input<T>, ExperimentationTransformer.Output<T>> {

    override fun apply(upstream: Observable<Input<T>>): ObservableSource<Output<T>> {
        return upstream
            .flatMap {
                val request = ExperimentationRepository.Request(it.experimentKey)
                experimentationRepository.isEnabled(request)
                    .flatMap { result -> result.toDataObservable() }
                    .map { isEnabled -> Output(isEnabled, it.experimentKey, it.data) }
            }
            .doOnNext { logEvent(it) }
    }

    private fun logEvent(result: Output<T>) {
        val attributes: MutableMap<String, Any?> = LinkedHashMap()
        attributes["message"] = "experiment"
        attributes["experimentKey"] = result.experimentKey
        attributes["enabled"] = result.isEnabled
        analyticsRepository.logEvent(result.experimentKey, attributes)
    }

    data class Input<T>(
        val experimentKey: String,
        val data: T
    )

    data class Output<T>(
        val isEnabled: Boolean,
        val experimentKey: String,
        val data: T
    )
}
