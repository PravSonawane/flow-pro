package domain.models.flow

import domain.models.core.Mappable

data class Flow(
    val id: String,
    val state: State,
    val name: String? = null,
    val description: String? = null,
    val steps: List<Step>? = null,
    val paths: List<Path>? = null
) : Mappable {
    enum class State {
        DRAFT
    }

    override fun toMap(): Map<String, Any?> {
        return mapper()
    }
}

fun Flow.mapper(): Map<String, Any?> {
    val map = LinkedHashMap<String, Any?>()
    map["id"] = id
    map["state"] = state
    map["name"] = name
    map["description"] = description
    map["steps"] = steps
    map["paths"] = paths
    return map
}
