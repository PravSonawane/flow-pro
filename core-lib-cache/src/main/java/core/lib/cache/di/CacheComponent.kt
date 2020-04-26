package core.lib.cache.di

import core.lib.cache.CacheRepository

interface CacheComponent {
    fun cacheRepository(): CacheRepository<String, Any>
}
