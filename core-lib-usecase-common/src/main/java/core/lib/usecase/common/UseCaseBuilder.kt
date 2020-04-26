package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase

interface UseCaseBuilder<Input : Any, Output : Any> {
    fun build(): ObservableResultUseCase<Input, Output>
}
