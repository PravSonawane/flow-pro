package domain.flow.models

data class Flow(
    val id: String,
    val name: String? = null,
    val description: String? = null
) {

    enum class FlowState {
        DRAFT
    }
}

data class SaveFlowRequest(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null
)