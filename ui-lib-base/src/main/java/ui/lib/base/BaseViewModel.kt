package ui.lib.base

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

abstract class BaseViewModel<T>(
    protected val eventStream: Subject<T> = BehaviorSubject.create(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {
    fun events(): Observable<T> {
        return eventStream.hide()
    }

    fun dispose() {
        compositeDisposable.dispose()
    }
}