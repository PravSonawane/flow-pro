package core.lib.plugin.impl.di

import core.lib.plugin.PluginRepository
import core.lib.plugin.di.PluginModule
import core.lib.plugin.impl.DefaultPluginRepository
import dagger.Module
import dagger.Provides

@Module(includes = [PluginModule::class])
class DefaultPluginModule {

    @Provides
    fun pluginRepository(): PluginRepository {
        return DefaultPluginRepository()
    }
}
