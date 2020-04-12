package domain.flow.usecases.save.flow

import domain.models.flow.Path
import domain.models.flow.Step

data class CreateFlowInput(
    val name: String? = null,
    val description: String? = null,
    val steps: List<Step>? = null,
    val paths: List<Path>? = null
)