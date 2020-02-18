package core.lib.usecase.common

import core.lib.plugin.Plugin

data class AnalyticsData<T>(
    val analyticsKey: String,
    val data: T
)

data class PluginData<T>(
    val plugin: Plugin,
    val data: AnalyticsData<T>
)

data class BusinessInput<T>(
    val input: T,
    val analyticsKey: String,
    val plugin: Plugin
)