package core.lib.usecase.common

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class PluginUseCase<Input, Output> @Inject constructor(
    @JvmSuppressWildcards val useCase: ObservableResultUseCase<Input, Output>,
    private val pluginTransformer: PluginTransformer<Input>
) : ObservableResultUseCase<PluginData<Input>, Output> {

    override fun invoke(input: PluginData<Input>): Observable<Result<Output>> {
        return Observable.just(input)
            .compose(pluginTransformer)
            .flatMap { useCase.invoke(input.data) }
    }
}
