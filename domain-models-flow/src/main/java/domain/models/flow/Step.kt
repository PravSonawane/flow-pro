package domain.models.flow

data class Step(
    val id: String,
    val description: String? = null,
    val node: Node? = null,
    val inputSteps: List<Step>? = null,
    val outputSteps: List<Step>? = null
)