package core.lib.usecase.common

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class ExperimentationUseCase<Input, Output> @Inject constructor(
    private val useCase1: ObservableResultUseCase<Input, Output>,
    private val useCase2: ObservableResultUseCase<Input, Output>,
    private val experimentationTransformer: ExperimentationTransformer<Input>
) : ObservableResultUseCase<ExperimentData<Input>, Output> {

    override fun invoke(input: ExperimentData<Input>): Observable<Result<Output>> {
        return Observable.just(input)
            .compose(experimentationTransformer)
            .flatMap {
                when {
                    it.isEnabled -> useCase1.invoke(input.data)
                    else -> useCase2.invoke(input.data)
                }
            }
    }
}
