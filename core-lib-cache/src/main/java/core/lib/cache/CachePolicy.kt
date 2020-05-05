package core.lib.cache

interface CachePolicy<Key, Data> {
    fun cacheKey(data: Data): CacheKey<Key>
}
