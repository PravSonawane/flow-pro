package core.lib.usecase.common

import core.lib.cache.CacheRepository
import core.lib.result.Result
import core.lib.result.toDataObservable
import core.lib.result.toObservable
import core.lib.result.toResult
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class CacheUseCase<Input : Any, Output : Any> @Inject constructor(
    private val analyticsKey: String,
    @JvmSuppressWildcards val useCase: ObservableResultUseCase<Input, Output>,
    private val cacheRepository: CacheRepository<String, Any>,
    @JvmSuppressWildcards val inputAnalyticsTransformer: InputAnalyticsTransformer<Input>,
    @JvmSuppressWildcards val outputAnalyticsTransformer: OutputAnalyticsTransformer<Output>
) : ObservableResultUseCase<Input, Output> {

    override fun invoke(input: Input): Observable<Result<Output>> {
        return Observable.just(input)
            .map { InputAnalyticsTransformer.Input(analyticsKey, input) }
            .compose(inputAnalyticsTransformer)
            .flatMap {
                cacheRepository.get(input.toString())
                    .map { it.toResult() as Result<Output> }
                    .flatMap { it.toObservable() }
                    .concatWith { useCase.invoke(input) }
                    .flatMap { it.toDataObservable() }
                    .flatMap {
                        cacheRepository.cache(input.toString(), it)
                            .toObservable()
                            .switchIfEmpty(Observable.just(it))
                    }
                    .map { it.toResult() as Result<Output> }
            }
            .flatMap { it.toDataObservable() }
            .map { OutputAnalyticsTransformer.Input(analyticsKey, it) }
            .compose(outputAnalyticsTransformer)
            .map { it.toResult() }
    }

    companion object {
        const val ANALYTICS_KEY = "fac4484b-c954"
    }
}
