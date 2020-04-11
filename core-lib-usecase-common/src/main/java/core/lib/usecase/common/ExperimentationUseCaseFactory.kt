package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase
import javax.inject.Inject

class ExperimentationUseCaseFactory<Input, Output> @Inject constructor(
    private val experimentationTransformer: ExperimentationTransformer<Input>
) {

    fun createV2(
        experimentKey: String,
        useCase1: ObservableResultUseCase<Input, Output>,
        useCase2: ObservableResultUseCase<Input, Output>
    ): ExperimentationUseCaseV2<Input, Output> {
        return ExperimentationUseCaseV2(
            experimentKey,
            useCase1,
            useCase2,
            experimentationTransformer
        )
    }
}
