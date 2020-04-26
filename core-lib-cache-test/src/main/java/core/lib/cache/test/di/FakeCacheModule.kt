package core.lib.cache.test.di

import core.lib.cache.CacheRepository
import core.lib.cache.di.CacheModule
import dagger.Module
import dagger.Provides

@Module(includes = [CacheModule::class])
class FakeCacheModule {

    @Provides
    fun cacheRepository(): CacheRepository<String, Any> {
        return FakeCacheRepository()
    }
}
