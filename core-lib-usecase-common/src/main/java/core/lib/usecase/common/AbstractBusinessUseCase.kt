package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase

abstract class AbstractBusinessUseCase<Input, Output> :
    ObservableResultUseCase<BusinessData<Input>, Output> {
}
