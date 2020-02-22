package core.lib.analytics

interface Analytics {
    fun logEvent(
        key: String,
        attributes: Map<String, Any?> = emptyMap(),
        priority: Priority = Priority.DEBUG
    )

    enum class Priority {
        DEBUG,
        INFO,
        ERROR
    }
}
