package core.lib.usecase.common

import core.lib.usecase.ObservableResultUseCase
import javax.inject.Inject

class BasicUseCaseBuilder<Input : Any, Output : Any> @Inject constructor(
    @JvmSuppressWildcards val analyticsUseCaseFactory: AnalyticsUseCaseFactory<Input, Output>,
    @JvmSuppressWildcards val pluginUseCaseFactory: PluginUseCaseFactory<Input, Output>,
    @JvmSuppressWildcards val experimentationUseCaseFactory: ExperimentationUseCaseFactory<Input, Output>
) : UseCaseBuilder<Input, Output> {

    private var useCase: ObservableResultUseCase<Input, Output>? = null

    fun compose(useCase: ObservableResultUseCase<Input, Output>): BasicUseCaseBuilder<Input, Output> {
        this.useCase = useCase
        return this
    }

    fun withAnalytics(analyticsKey: String): BasicUseCaseBuilder<Input, Output> {
        require(useCase != null) { noUseCaseSuppliedMessage() }
        useCase = analyticsUseCaseFactory.create(analyticsKey, useCase!!)
        return this
    }

    fun withPlugin(pluginKey: String): BasicUseCaseBuilder<Input, Output> {
        require(useCase != null) { noUseCaseSuppliedMessage() }
        useCase = pluginUseCaseFactory.create(pluginKey, useCase!!)
        return this
    }

    fun withExperiment(
        experimentKey: String,
        treatmentUseCase: ObservableResultUseCase<Input, Output>
    ): BasicUseCaseBuilder<Input, Output> {
        require(useCase != null) { noUseCaseSuppliedMessage() }
        useCase = experimentationUseCaseFactory.createV2(experimentKey, useCase!!, treatmentUseCase)
        return this
    }

    override fun build(): ObservableResultUseCase<Input, Output> {
        require(useCase != null) { noUseCaseSuppliedMessage() }
        return useCase!!
    }

    private fun noUseCaseSuppliedMessage(): String {
        return "UseCase not supplied. Call compose(useCase) before any other BasicUseCaseBuilder methods"
    }
}
