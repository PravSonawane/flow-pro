package core.lib.usecase

import io.reactivex.Observable
import core.lib.result.Result

interface ObservableResultUseCase<Input, Output> : UseCase<Input, Observable<Result<Output>>> {
    override operator fun invoke(input: Input): Observable<Result<Output>>
}