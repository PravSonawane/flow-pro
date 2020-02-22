package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase
import javax.inject.Inject

class BusinessUseCaseFactory<Input, Output> @Inject constructor(
    private val useCase: ObservableResultUseCase<Input, Output>,
    private val inputAnalyticsTransformer: InputAnalyticsTransformer<Input>,
    private val outputAnalyticsTransformer: OutputAnalyticsTransformer<Output>,
    private val pluginTransformer: PluginTransformer<AnalyticsData<Input>>
) {
}