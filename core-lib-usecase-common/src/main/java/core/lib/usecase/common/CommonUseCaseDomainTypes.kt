package core.lib.usecase.common

import core.lib.plugin.Plugin

data class AnalyticsData<T>(
    val analyticsKey: String,
    val data: T
)

data class PluginData<T>(
    val plugin: Plugin,
    val data: T
)

data class BusinessData<T>(
    val analyticsKey: String,
    val plugin: Plugin,
    val data: T
)
