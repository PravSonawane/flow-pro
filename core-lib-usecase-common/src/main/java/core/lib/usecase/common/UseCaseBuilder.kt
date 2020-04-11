package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase

interface UseCaseBuilder<Input, Output> {
    fun build(): ObservableResultUseCase<Input, Output>
}