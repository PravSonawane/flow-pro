package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase
import javax.inject.Inject

class AnalyticsUseCaseFactory<Input, Output> @Inject constructor(
    @JvmSuppressWildcards val inputAnalyticsTransformer: InputAnalyticsTransformer<Input>,
    @JvmSuppressWildcards val outputAnalyticsTransformer: OutputAnalyticsTransformer<Output>
) {

    fun create(
        analyticsKey: String,
        useCase: ObservableResultUseCase<Input, Output>
    ): AnalyticsUseCase<Input, Output> {
        return AnalyticsUseCase(
            analyticsKey,
            useCase,
            inputAnalyticsTransformer,
            outputAnalyticsTransformer
        )
    }
}
