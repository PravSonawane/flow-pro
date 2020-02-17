package core.lib.usecase.common

import core.lib.analytics.Analytics
import core.lib.analytics.AnalyticsRepository
import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class AnalyticsUseCase<T> @Inject constructor(
    private val useCase: ObservableResultUseCase<AnalyticsData<T>, T>,
    private val analyticsRepository: AnalyticsRepository
) : ObservableResultUseCase<AnalyticsData<T>, T> {

    override fun invoke(input: AnalyticsData<T>): Observable<Result<T>> {
        return Observable.just(input)
            .doOnNext { logEvent(it) }
            .flatMap { useCase.invoke(it) }
    }

    private fun logEvent(it: AnalyticsData<T>) {
        val attributes: Map<String, String> = mapOf(
            "analyticsKey" to it.analyticsKey,
            "data" to it.data.toString()
        )
        analyticsRepository.logEvent(Analytics.KEY_DEBUG, attributes)
    }
}