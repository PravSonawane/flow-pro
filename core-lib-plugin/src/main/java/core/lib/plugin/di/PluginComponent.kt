package core.lib.plugin.di

import core.lib.plugin.PluginRepository

interface PluginComponent {
    fun pluginRepository(): PluginRepository
}
