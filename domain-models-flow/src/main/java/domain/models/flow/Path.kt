package domain.models.flow

import domain.models.core.Mappable

data class Path(
    val id: String,
    val name: String? = null,
    val description: String? = null,
    val steps: List<Step>? = null
) : Mappable {

    override fun toMap(): Map<String, Any?> {
        return mapper()
    }
}

fun Path.mapper(): Map<String, Any?> {
    val map = LinkedHashMap<String, Any?>()
    map["id"] = id
    map["name"] = name
    map["description"] = description
    map["steps"] = steps
    return map
}
