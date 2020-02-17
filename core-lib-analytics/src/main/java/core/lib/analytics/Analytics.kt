package core.lib.analytics

interface Analytics {
    fun logEvent(key: String, attributes: Map<String, Any?> = emptyMap())

    companion object {
        const val KEY_DEBUG = "debug"
    }
}
