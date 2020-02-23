package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase
import javax.inject.Inject

class BusinessUseCaseFactory<Input, Output> @Inject constructor(
    private val inputAnalyticsTransformer: InputAnalyticsTransformer<Input>,
    private val outputAnalyticsTransformer: OutputAnalyticsTransformer<Output>,
    private val pluginTransformer: PluginTransformer<AnalyticsData<Input>>
) {

    fun create(useCase: ObservableResultUseCase<Input, Output>): BusinessUseCase<Input, Output> {
        return BusinessUseCase(
            useCase,
            inputAnalyticsTransformer,
            outputAnalyticsTransformer,
            pluginTransformer
        )
    }
}
