package ui.feature.flow.steplist

import androidx.lifecycle.MutableLiveData
import core.lib.rxutils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class IncompleteInputViewModel @Inject constructor(
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory
) : BaseViewModel<IncompleteInputViewModel.Input, IncompleteInputViewModel.Event>(
    "c68f8543-e30d",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val message: MutableLiveData<String> =
        liveDataFactory.mutableLiveData("838403e6-4871")

    init {

        compositeDisposable += observeInput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                message.value = it.message
            }
    }

    data class Input(val message: String)

    sealed class Event

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
