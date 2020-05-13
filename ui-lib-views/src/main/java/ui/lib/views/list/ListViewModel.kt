package ui.lib.views.list

import androidx.lifecycle.MutableLiveData
import core.lib.rxutils.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.base.ItemViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory

class ListViewModel<T : ItemViewModel<ListViewModel.ItemInput, ListViewModel.ItemOutput>>(
    analyticsKey: String,
    liveDataFactory: LiveDataFactory,
    streamFactory: StreamFactory
) : BaseViewModel<ListViewModel.Input<T>, ListViewModel.Output<T>>(
    analyticsKey,
    streamFactory
) {
    private val compositeDisposable = CompositeDisposable()
    val items: MutableLiveData<List<T>> = liveDataFactory.mutableLiveData(analyticsKey, emptyList())

    init {
        compositeDisposable += observeInput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Input.OnData -> this.items.value = it.items
                }
            }

        items.observeForever {
            compositeDisposable += Observable.merge(it.map { item -> item.observeOutput() })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { event -> sendOutput(Output.OnItemOutput(event)) }
        }
    }

    sealed class Input<T> {
        data class OnData<T>(val items: List<T>) : Input<T>()
        class OnLoading<T> : Input<T>()
    }

    sealed class Output<T> {
        data class OnItemOutput<T>(val itemOutput: ItemOutput): Output<T>()
    }

    interface ItemInput
    interface ItemOutput

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
