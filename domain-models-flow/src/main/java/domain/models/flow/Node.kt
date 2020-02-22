package domain.models.flow

import domain.models.core.Mappable

data class Node(
    val id: String,
    val name: String? = null,
    val description: String? = null
) : Mappable {

    override fun toMap(): Map<String, Any?> {
        return mapper()
    }
}

fun Node.mapper(): Map<String, Any?> {
    val map = LinkedHashMap<String, Any?>()
    map["id"] = id
    map["name"] = name
    map["description"] = description
    return map
}
