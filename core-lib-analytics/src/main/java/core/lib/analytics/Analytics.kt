package core.lib.analytics

interface Analytics {
    fun logEvent(key: String, attributes: Map<String, Any>)
}
