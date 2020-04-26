package core.lib.usecase.common

import core.lib.result.Result
import core.lib.result.toData
import core.lib.result.toResult
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class AnalyticsUseCase<Input : Any, Output : Any> @Inject constructor(
    private val analyticsKey: String,
    @JvmSuppressWildcards val useCase: ObservableResultUseCase<Input, Output>,
    @JvmSuppressWildcards val inputAnalyticsTransformer: InputAnalyticsTransformer<Input>,
    @JvmSuppressWildcards val outputAnalyticsTransformer: OutputAnalyticsTransformer<Output>
) : ObservableResultUseCase<Input, Output> {

    override fun invoke(input: Input): Observable<Result<Output>> {
        return Observable.just(input)
            .map { InputAnalyticsTransformer.Input(analyticsKey, input) }
            .compose(inputAnalyticsTransformer)
            .flatMap { useCase.invoke(it) }
            .flatMap { it.toData() }
            .map { OutputAnalyticsTransformer.Input(analyticsKey, it) }
            .compose(outputAnalyticsTransformer)
            .map { it.toResult() }
    }
}
