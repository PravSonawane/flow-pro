package ui.newflow.title

import androidx.lifecycle.MutableLiveData
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import domain.flow.models.SaveFlowInput
import domain.flow.usecases.SaveOrUpdateFlowUseCase
import domain.models.flow.Flow
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.StreamFactory
import javax.inject.Inject

internal const val DEFAULT_FLOW_NAME_PREFIX = "My Flow "

class NewFlowTitleViewModel @Inject constructor(
    streamFactory: StreamFactory,
    private val saveOrUpdateFlowUseCase: SaveOrUpdateFlowUseCase
) : BaseViewModel<NewFlowTitleViewModel.Input, NewFlowTitleViewModel.Event>(
    "718adc21-f9c0",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var title: MutableLiveData<String> = MutableLiveData("$DEFAULT_FLOW_NAME_PREFIX 1")
    var error: String = ""

    fun onNext() {
        compositeDisposable += saveOrUpdateFlowUseCase.invoke(SaveFlowInput(title.value))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleSaveOrUpdateSuccess(it.data)
                }
            }
    }

    private fun handleSaveOrUpdateSuccess(data: Flow) {
        sendOutput(Event.OnNext(data))
    }

    sealed class Input

    sealed class Event {
        data class OnNext(val flow: Flow) : Event()
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}
