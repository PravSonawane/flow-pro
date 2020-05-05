package core.lib.cache

class StringCachePolicy<T> : CachePolicy<String, T> {
    override fun cacheKey(data: T): CacheKey<String> {
        return StringCacheKey(data.toString())
    }
}
