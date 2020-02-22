package core.lib.usecase.common

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class BusinessUseCase<Input, Output> @Inject constructor(
    private val useCase: ObservableResultUseCase<Input, Output>,
    private val inputAnalyticsTransformer: InputAnalyticsTransformer<Input>,
    private val outputAnalyticsTransformer: OutputAnalyticsTransformer<Output>,
    private val pluginTransformer: PluginTransformer<AnalyticsData<Input>>
) : ObservableResultUseCase<BusinessData<Input>, Output> {

    override fun invoke(input: BusinessData<Input>): Observable<Result<Output>> {
        val pluginData = PluginData(input.plugin, AnalyticsData(input.analyticsKey, input.data))
        val analyticsUseCase = AnalyticsUseCase(useCase, inputAnalyticsTransformer, outputAnalyticsTransformer)
        val pluginUseCase = PluginUseCase(analyticsUseCase, pluginTransformer)
        return Observable.just(pluginData)
            .flatMap { pluginUseCase.invoke(it) }
    }
}
