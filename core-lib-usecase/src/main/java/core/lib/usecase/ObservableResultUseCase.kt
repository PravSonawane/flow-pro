package core.lib.usecase

import io.reactivex.Observable
import domain.core.result.Result

interface ObservableResultUseCase<Input, Output> {
    operator fun invoke(input: Input): Observable<Result<Output>>
}