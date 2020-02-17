package core.lib.analytics.impl

import android.util.Log
import core.lib.analytics.Analytics
import javax.inject.Inject

class LoggingAnalytics @Inject constructor() : Analytics {

    override fun logEvent(key: String, attributes: Map<String, Any?>) {
        Log.d(key, "logs:$attributes")
    }
}