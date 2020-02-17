package core.lib.usecase.common

import core.lib.analytics.Analytics
import core.lib.analytics.AnalyticsRepository
import core.lib.result.Result
import core.lib.result.toData
import core.lib.result.toResult
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class AnalyticsUseCase<Input, Output> @Inject constructor(
    private val useCase: ObservableResultUseCase<Input, Output>,
    private val analyticsRepository: AnalyticsRepository
) : ObservableResultUseCase<AnalyticsData<Input>, Output> {

    override fun invoke(input: AnalyticsData<Input>): Observable<Result<Output>> {
        return Observable.just(input)
            .doOnNext { logInput(it) }
            .flatMap { useCase.invoke(it.data) }
            .flatMap { it.toData() }
            .map { AnalyticsData(input.analyticsKey, it) }
            .doOnNext { logOutput(it) }
            .map { it.data }
            .map { it.toResult() }
    }

    private fun logInput(it: AnalyticsData<Input>) {
        val attributes: Map<String, String> = mapOf(
            "analyticsKey" to it.analyticsKey,
            "input" to it.data.toString()
        )
        analyticsRepository.logEvent(Analytics.KEY_DEBUG, attributes)
    }

    private fun logOutput(it: AnalyticsData<Output>) {
        val attributes: Map<String, String> = mapOf(
            "analyticsKey" to it.analyticsKey,
            "output" to it.data.toString()
        )
        analyticsRepository.logEvent(Analytics.KEY_DEBUG, attributes)
    }
}