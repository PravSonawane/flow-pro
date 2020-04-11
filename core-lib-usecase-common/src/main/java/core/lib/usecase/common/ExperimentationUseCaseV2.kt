package core.lib.usecase.common

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class ExperimentationUseCaseV2<Input, Output> @Inject constructor(
    private val experimentKey: String,
    @JvmSuppressWildcards val useCase1: ObservableResultUseCase<Input, Output>,
    @JvmSuppressWildcards val useCase2: ObservableResultUseCase<Input, Output>,
    @JvmSuppressWildcards val experimentationTransformer: ExperimentationTransformer<Input>
) : ObservableResultUseCase<Input, Output> {

    override fun invoke(input: Input): Observable<Result<Output>> {
        return Observable.just(input)
            .map { ExperimentData(experimentKey, input) }
            .compose(experimentationTransformer)
            .flatMap {
                when {
                    it.isEnabled -> useCase2.invoke(input)
                    else -> useCase1.invoke(input)
                }
            }
    }
}
