package core.lib.analytics.firebase

import android.content.Context
import androidx.core.os.bundleOf
import app.di.annotations.ApplicationContext
import com.google.firebase.analytics.FirebaseAnalytics
import core.lib.analytics.Analytics
import javax.inject.Inject

class FirebaseAnalytics @Inject constructor(
    @ApplicationContext private val context: Context
) : Analytics {
    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun logEvent(key: String, attributes: Map<String, Any?>, priority: Analytics.Priority) {
        val mutableMap = LinkedHashMap<String, String>()
        var newKey = key

        // all debug analytics have the same key, viz. 'debug'
        if (priority == Analytics.Priority.DEBUG) {
            newKey = KEY_DEBUG
            mutableMap["analyticsKey"] = key
            attributes.forEach {
                mutableMap[it.key] = it.value.toString().take(1000)
            }
        }

        val bundle = bundleOf(*mutableMap.toList().toTypedArray())
        firebaseAnalytics.logEvent(newKey, bundle)
    }

    companion object {
        const val KEY_DEBUG = "debug"
    }
}
