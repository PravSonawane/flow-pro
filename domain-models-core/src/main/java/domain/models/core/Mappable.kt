package domain.models.core

interface Mappable {
    fun toMap(): Map<String, Any?>
}