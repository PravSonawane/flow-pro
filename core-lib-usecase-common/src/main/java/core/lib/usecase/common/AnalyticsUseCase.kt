package core.lib.usecase.common

import core.lib.result.Result
import core.lib.result.toData
import core.lib.result.toResult
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class AnalyticsUseCase<Input, Output> @Inject constructor(
    @JvmSuppressWildcards val useCase: ObservableResultUseCase<Input, Output>,
    @JvmSuppressWildcards val inputAnalyticsTransformer: InputAnalyticsTransformer<Input>,
    @JvmSuppressWildcards val outputAnalyticsTransformer: OutputAnalyticsTransformer<Output>
) : ObservableResultUseCase<AnalyticsData<Input>, Output> {

    override fun invoke(input: AnalyticsData<Input>): Observable<Result<Output>> {
        return Observable.just(input)
            .compose(inputAnalyticsTransformer)
            .flatMap { useCase.invoke(it) }
            .flatMap { it.toData() }
            .map { AnalyticsData(input.analyticsKey, it) }
            .compose(outputAnalyticsTransformer)
            .map { it.toResult() }
    }
}
