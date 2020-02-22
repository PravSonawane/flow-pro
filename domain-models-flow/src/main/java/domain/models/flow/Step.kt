package domain.models.flow

import domain.models.core.Mappable

data class Step(
    val id: String,
    val name: String? = null,
    val description: String? = null,
    val node: Node? = null,
    val inputSteps: List<Step>? = null,
    val outputSteps: List<Step>? = null
) : Mappable {
    override fun toMap(): Map<String, Any?> {
        return mapper()
    }
}

fun Step.mapper(): Map<String, Any?> {
    val map = LinkedHashMap<String, Any?>()
    map["id"] = id
    map["name"] = name
    map["description"] = description
    map["node"] = node
    map["inputSteps"] = inputSteps
    map["outputSteps"] = outputSteps
    return map
}
