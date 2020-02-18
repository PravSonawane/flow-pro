package core.lib.plugin.impl.di

import core.lib.plugin.PluginRepository

interface PluginComponent {
    fun pluginRepository(): PluginRepository
}
