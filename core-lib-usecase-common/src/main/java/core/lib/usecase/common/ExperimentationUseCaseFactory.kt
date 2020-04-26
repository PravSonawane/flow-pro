package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase
import javax.inject.Inject

class ExperimentationUseCaseFactory<Input : Any, Output : Any> @Inject constructor(
    @JvmSuppressWildcards val experimentationTransformer: ExperimentationTransformer<Input>
) {

    fun createV2(
        experimentKey: String,
        useCase1: ObservableResultUseCase<Input, Output>,
        useCase2: ObservableResultUseCase<Input, Output>
    ): ExperimentationUseCase<Input, Output> {
        return ExperimentationUseCase(
            experimentKey,
            useCase1,
            useCase2,
            experimentationTransformer
        )
    }
}
