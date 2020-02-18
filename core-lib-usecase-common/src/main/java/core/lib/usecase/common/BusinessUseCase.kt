package core.lib.usecase.common

import core.lib.analytics.AnalyticsRepository
import core.lib.plugin.PluginRepository
import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class BusinessUseCase<Input, Output> @Inject constructor(
    private val useCase: ObservableResultUseCase<Input, Output>,
    private val pluginRepository: PluginRepository,
    private val analyticsRepository: AnalyticsRepository
) : ObservableResultUseCase<BusinessInput<Input>, Output> {

    override fun invoke(input: BusinessInput<Input>): Observable<Result<Output>> {
        val analyticsUseCase = AnalyticsUseCase(useCase, analyticsRepository)
        val pluginUseCase = PluginUseCase(analyticsUseCase, analyticsRepository, pluginRepository)
        return Observable.just(input)
            .flatMap { pluginUseCase.invoke(PluginData(it.plugin, AnalyticsData(it.analyticsKey, it.input))) }
    }
}