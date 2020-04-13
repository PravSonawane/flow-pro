package ui.navigation.usecases

import core.lib.analytics.AnalyticsRepository
import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import ui.navigation.NavigationConfig
import javax.inject.Inject

class LogNavigationUseCase @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) : ObservableResultUseCase<NavigationConfig, NavigationConfig> {
    override fun invoke(input: NavigationConfig): Observable<Result<NavigationConfig>> {
        analyticsRepository.logEvent(ANALYTICS_KEY, createAttributes(input))
        return Observable.just(Result.OnSuccess(input))
    }

    private fun createAttributes(navigationConfig: NavigationConfig): Map<String, Any?> {
        val attributes: MutableMap<String, Any?> = LinkedHashMap()
        attributes["message"] = "navigation"
        attributes.putAll(navigationConfig.toMap())
        return attributes
    }

    companion object {
        const val NAMED = "ui.navigation.usecases.LogNavigationUseCase"
        const val ANALYTICS_KEY = "b1957339-ddef"
        const val PLUGIN_KEY = "e74ea9a6-af53"
    }
}