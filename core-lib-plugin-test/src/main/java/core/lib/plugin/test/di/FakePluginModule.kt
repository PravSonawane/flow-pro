package core.lib.plugin.test.di

import core.lib.plugin.PluginRepository
import core.lib.plugin.di.PluginModule
import dagger.Module
import dagger.Provides

@Module(includes = [PluginModule::class])
class FakePluginModule {

    @Provides
    fun pluginRepository(): PluginRepository {
        return FakePluginRepository()
    }
}
