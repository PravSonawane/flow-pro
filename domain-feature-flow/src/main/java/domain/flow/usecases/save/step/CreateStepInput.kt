package domain.flow.usecases.save.step

import domain.models.flow.Node
import domain.models.flow.Step

data class CreateStepInput(
    val name: String? = null,
    val description: String? = null,
    val node: Node? = null,
    val inputSteps: List<Step>? = null,
    val outputSteps: List<Step>? = null
)