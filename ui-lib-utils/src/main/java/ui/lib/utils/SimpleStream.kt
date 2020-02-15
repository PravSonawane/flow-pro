package ui.lib.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class SimpleStream<T> @Inject constructor() : Stream<T> {
    private val stream: Subject<T> = PublishSubject.create()

    override fun publish(data: T) {
        stream.onNext(data)
    }

    override fun subscribe(): Observable<T> {
        return stream.hide()
    }
}
