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
    ): AnalyticsUseCaseV2<Input, Output> {
        return AnalyticsUseCaseV2(
            analyticsKey,
            useCase,
            inputAnalyticsTransformer,
            outputAnalyticsTransformer
        )
    }
}
