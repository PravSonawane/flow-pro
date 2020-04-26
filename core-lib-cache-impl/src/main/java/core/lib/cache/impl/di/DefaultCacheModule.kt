package core.lib.cache.impl.di

import core.lib.cache.CacheRepository
import core.lib.cache.di.CacheModule
import core.lib.cache.impl.DefaultCacheRepository
import dagger.Module
import dagger.Provides

@Module(includes = [CacheModule::class])
class DefaultCacheModule {

    @Provides
    fun cacheRepository(): CacheRepository<String, Any> {
        return DefaultCacheRepository()
    }
}
