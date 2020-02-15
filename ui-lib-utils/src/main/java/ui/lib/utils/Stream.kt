package ui.lib.utils

import io.reactivex.Observable

interface Stream<T> {
    fun publish(data: T)
    fun subscribe(): Observable<T>
}