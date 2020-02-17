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

    override fun logEvent(key: String, attributes: Map<String, Any?>) {
        val bundle = bundleOf(*attributes.toList().toTypedArray())
        firebaseAnalytics.logEvent(key, bundle)
    }
}