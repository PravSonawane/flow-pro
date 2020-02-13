package domain.models.flow

data class Flow(
    val id: String,
    val state: State,
    val name: String? = null,
    val description: String? = null,
    val steps: List<Step>? = null,
    val paths: List<Path>? = null
) {

    enum class State {
        DRAFT
    }
}