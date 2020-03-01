package core.lib.analytics.impl

import android.util.Log
import core.lib.analytics.Analytics
import core.lib.rxutils.plusAssign
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoggingAnalytics @Inject constructor() : Analytics {

    private val compositeDisposable = CompositeDisposable()
    private val logStream: Subject<Pair<String, Map<String, Any?>>> = PublishSubject.create()

    init {
        compositeDisposable += logStream
            .buffer(1, TimeUnit.SECONDS)
            .flatMapIterable { it }
            .subscribe {
                Log.d(it.first, "logs:${it.second}")
            }
    }

    override fun logEvent(key: String, attributes: Map<String, Any?>, priority: Analytics.Priority) {
        logStream.onNext(Pair(key, attributes))
    }
}
