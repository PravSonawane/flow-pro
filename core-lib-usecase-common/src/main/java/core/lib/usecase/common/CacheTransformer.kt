package core.lib.usecase.common

import core.lib.analytics.AnalyticsRepository
import core.lib.cache.CacheRepository
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class CacheTransformer<T> @Inject constructor(
    private val analyticsRepository: AnalyticsRepository,
    @JvmSuppressWildcards val cacheRepository: CacheRepository<String, T>
) : ObservableTransformer<CacheTransformer.Input<T>, T> {

    override fun apply(upstream: Observable<Input<T>>): ObservableSource<T> {
        return upstream
            .doOnNext { log(it) }
            .flatMap { cacheRepository.get(it.cacheKey) }
    }

    private fun log(it: Input<T>) {
        val attributes: MutableMap<String, Any?> = LinkedHashMap()
        attributes["message"] = "cache"

        attributes["cacheKey"] = it.cacheKey

        analyticsRepository.logEvent(it.analyticsKey, attributes)
    }

    data class Input<T>(
        val analyticsKey: String,
        val cacheKey: String
    )
}
