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

data class ExperimentData<T>(
    val experimentKey: String,
    val data: T
)

data class ExperimentResult<T>(
    val isEnabled: Boolean,
    val experimentKey: String,
    val data: T
)
