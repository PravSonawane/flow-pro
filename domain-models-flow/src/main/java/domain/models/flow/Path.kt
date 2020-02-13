package domain.models.flow

data class Path(
    val id: String,
    val name: String? = null,
    val description: String? = null,
    val steps: List<Step>? = null
)