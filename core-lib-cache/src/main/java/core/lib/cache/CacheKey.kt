package core.lib.cache

interface CacheKey<T> {

    fun get(): T
}