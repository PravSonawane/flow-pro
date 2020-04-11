package core.lib.usecase.common

data class ExperimentData<T>(
    val experimentKey: String,
    val data: T
)

data class ExperimentResult<T>(
    val isEnabled: Boolean,
    val experimentKey: String,
    val data: T
)
