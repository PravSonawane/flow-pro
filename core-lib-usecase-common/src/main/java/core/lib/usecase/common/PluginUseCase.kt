package core.lib.usecase.common

import core.lib.analytics.Analytics
import core.lib.analytics.AnalyticsRepository
import core.lib.plugin.PluginRepository
import core.lib.result.Result
import core.lib.result.toData
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import javax.inject.Inject

class PluginUseCase<T> @Inject constructor(
    private val useCase: ObservableResultUseCase<T, T>,
    private val analyticsRepository: AnalyticsRepository,
    private val pluginRepository: PluginRepository
) : ObservableResultUseCase<PluginData<T>, T> {

    override fun invoke(input: PluginData<T>): Observable<Result<T>> {
        return pluginRepository.isEnabled(input.plugin)
            .flatMap { it.toData() }
            .doOnNext { logEvent(input.plugin.pluginKey, it) }
            .filter { it }
            .flatMap { useCase.invoke(input.data) }
    }

    private fun logEvent(pluginKey: String, isEnabled: Boolean) {
        val attributes: Map<String, String> = mapOf(
            "pluginKey" to pluginKey,
            "enabled" to isEnabled.toString()
        )
        analyticsRepository.logEvent(Analytics.KEY_DEBUG, attributes)
    }
}