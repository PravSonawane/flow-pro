package domain.models.flow

data class Flow(
    val id: String,
    val name: String? = null,
    val description: String? = null
) {

    enum class FlowState {
        DRAFT
    }
}