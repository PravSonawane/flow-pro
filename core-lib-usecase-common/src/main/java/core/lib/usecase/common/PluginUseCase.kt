package core.lib.usecase.common

import core.lib.plugin.Plugin
import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class PluginUseCase<Input, Output> @Inject constructor(
    private val pluginKey: String,
    @JvmSuppressWildcards val useCase: ObservableResultUseCase<Input, Output>,
    @JvmSuppressWildcards val pluginTransformer: PluginTransformer<Input>
) : ObservableResultUseCase<Input, Output> {

    override fun invoke(input: Input): Observable<Result<Output>> {
        return Observable.just(input)
            .map { PluginTransformer.Input(Plugin(pluginKey), input) }
            .compose(pluginTransformer)
            .flatMap { useCase.invoke(input) }
    }
}
