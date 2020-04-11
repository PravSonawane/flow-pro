package core.lib.usecase.common

import core.lib.analytics.AnalyticsRepository
import core.lib.plugin.Plugin
import core.lib.plugin.PluginRepository
import core.lib.result.toData
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class PluginTransformer<T> @Inject constructor(
    private val analyticsRepository: AnalyticsRepository,
    private val pluginRepository: PluginRepository
) : ObservableTransformer<PluginTransformer.Input<T>, T> {

    override fun apply(upstream: Observable<Input<T>>): ObservableSource<T> {
        return upstream
            .flatMap {
                pluginRepository.isEnabled(it.plugin)
                    .flatMap { result -> result.toData() }
                    .map { isEnabled -> Pair(it, isEnabled) }
            }
            .doOnNext { logEvent(it.first.plugin.pluginKey, it.second) }
            .filter { it.second }
            .flatMap { upstream.map { it.data } }
    }

    private fun logEvent(pluginKey: String, isEnabled: Boolean) {
        val attributes: MutableMap<String, Any?> = LinkedHashMap()
        attributes["message"] = "plugin"
        attributes["enabled"] = isEnabled
        analyticsRepository.logEvent(pluginKey, attributes)
    }

    data class Input<T>(
        val plugin: Plugin,
        val data: T
    )
}
