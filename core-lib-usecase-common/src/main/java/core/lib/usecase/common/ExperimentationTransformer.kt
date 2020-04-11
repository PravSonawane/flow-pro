package core.lib.usecase.common

import core.lib.analytics.AnalyticsRepository
import core.lib.experimentation.ExperimentationRepository
import core.lib.result.toData
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class ExperimentationTransformer<T> @Inject constructor(
    private val analyticsRepository: AnalyticsRepository,
    private val experimentationRepository: ExperimentationRepository
) : ObservableTransformer<ExperimentData<T>, ExperimentResult<T>> {

    override fun apply(upstream: Observable<ExperimentData<T>>): ObservableSource<ExperimentResult<T>> {
        return upstream
            .flatMap {
                val request = ExperimentationRepository.Request(it.experimentKey)
                experimentationRepository.isEnabled(request)
                    .flatMap { result -> result.toData() }
                    .map { isEnabled -> ExperimentResult(isEnabled, it.experimentKey, it.data) }
            }
            .doOnNext { logEvent(it) }
    }

    private fun logEvent(result: ExperimentResult<T>) {
        val attributes: MutableMap<String, Any?> = LinkedHashMap()
        attributes["message"] = "experiment"
        attributes["experimentKey"] = result.experimentKey
        attributes["enabled"] = result.isEnabled
        analyticsRepository.logEvent(result.experimentKey, attributes)
    }
}
