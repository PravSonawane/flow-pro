package core.lib.analytics.firebase

import android.content.Context
import androidx.core.os.bundleOf
import app.di.annotations.ApplicationContext
import com.google.firebase.analytics.FirebaseAnalytics
import core.lib.analytics.Analytics
import core.lib.rxutils.plusAssign
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FirebaseAnalytics @Inject constructor(
    @ApplicationContext private val context: Context
) : Analytics {
    private val compositeDisposable = CompositeDisposable()
    private val logStream: Subject<Triple<String, Map<String, Any?>, Analytics.Priority>> = PublishSubject.create()
    private val instance = FirebaseAnalytics.getInstance(context)

    init {
        compositeDisposable += logStream
            .buffer(1, TimeUnit.SECONDS)
            .flatMapIterable { it }
            .subscribe {
                val mutableMap = LinkedHashMap<String, String>()
                val key = it.first
                val attributes = it.second
                val priority = it.third

                var newKey = key

                // all debug analytics have the same key, viz. 'debug'
                if (priority == Analytics.Priority.DEBUG) {
                    newKey = KEY_DEBUG
                    mutableMap["analyticsKey"] = key
                    attributes.forEach { entry ->
                        // firebase key limit = 40 chars
                        // firebase value limit = 100 chars
                        mutableMap[entry.key.take(40)] = entry.value.toString().take(100)
                    }
                }

                val bundle = bundleOf(*mutableMap.toList().toTypedArray())
                instance.logEvent(newKey, bundle)
            }
    }

    override fun logEvent(key: String, attributes: Map<String, Any?>, priority: Analytics.Priority) {
        logStream.onNext(Triple(key, attributes, priority))
    }

    companion object {
        const val KEY_DEBUG = "debug"
    }
}
