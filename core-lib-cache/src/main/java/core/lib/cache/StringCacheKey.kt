package core.lib.cache

data class StringCacheKey(val key: String) : CacheKey<String> {
    override fun get(): String {
        return key
    }
}
