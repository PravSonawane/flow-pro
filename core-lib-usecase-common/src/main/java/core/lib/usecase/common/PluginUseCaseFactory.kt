package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase
import javax.inject.Inject

class PluginUseCaseFactory<Input : Any, Output : Any> @Inject constructor(
    @JvmSuppressWildcards val pluginTransformer: PluginTransformer<Input>
) {

    fun create(
        pluginKey: String,
        useCase: ObservableResultUseCase<Input, Output>
    ): PluginUseCase<Input, Output> {
        return PluginUseCase(
            pluginKey,
            useCase,
            pluginTransformer
        )
    }
}
