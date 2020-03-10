package ui.lib.views.list

import androidx.lifecycle.MutableLiveData
import core.lib.rxutils.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel

class ListViewModel<T : ItemViewModel<ListViewModel.ItemInput, ListViewModel.ItemOutput>>(
    analyticsKey: String,
    liveDataFactory: LiveDataFactory,
    streamFactory: StreamFactory
) : BaseViewModel<ListViewModel.Input<T>, ListViewModel.ItemOutput>(
    analyticsKey,
    streamFactory
) {
    private val compositeDisposable = CompositeDisposable()
    val items: MutableLiveData<List<T>> = liveDataFactory.mutableLiveData(analyticsKey, emptyList())

    init {
        compositeDisposable += observeInput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { this.items.value = it.items }

        items.observeForever {
            compositeDisposable += Observable.merge(it.map { item -> item.observeOutput() })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { event -> sendOutput(event) }
        }
    }

    data class Input<T>(val items: List<T>)
    interface ItemInput
    interface ItemOutput

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
