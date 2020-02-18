package core.lib.plugin.impl.di

import core.lib.plugin.PluginRepository
import core.lib.plugin.impl.DefaultPluginRepository
import dagger.Module
import dagger.Provides

@Module
class PluginModule {

    @Provides
    fun pluginRepository(): PluginRepository {
        return DefaultPluginRepository()
    }
}