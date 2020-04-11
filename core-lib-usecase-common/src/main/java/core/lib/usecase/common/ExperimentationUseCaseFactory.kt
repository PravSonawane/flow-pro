package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase
import javax.inject.Inject

class ExperimentationUseCaseFactory<Input, Output> @Inject constructor(
    private val experimentationTransformer: ExperimentationTransformer<Input>
) {

    fun create(
        useCase1: ObservableResultUseCase<Input, Output>,
        useCase2: ObservableResultUseCase<Input, Output>
    ): ExperimentationUseCase<Input, Output> {
        return ExperimentationUseCase(
            useCase1,
            useCase2,
            experimentationTransformer
        )
    }
}
