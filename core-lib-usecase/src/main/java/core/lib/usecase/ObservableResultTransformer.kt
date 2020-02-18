package core.lib.usecase

import io.reactivex.Observable
import core.lib.result.Result
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

interface ObservableResultTransformer<Input, Output> : ObservableTransformer<Input, Result<Output>> {
    override fun apply(upstream: Observable<Input>): ObservableSource<Result<Output>>
}
