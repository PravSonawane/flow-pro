package ui.lib.utils

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class InputStream<T> @Inject constructor() {
    private val stream: Subject<T> = BehaviorSubject.create()

    fun publish(data: T) {
        stream.onNext(data)
    }

    fun subscribe(): Observable<T> {
        return stream.hide()
    }
}