package ui.feature.newflow.title

import androidx.lifecycle.MutableLiveData
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import domain.flow.usecases.save.flow.CreateFlowInput
import domain.flow.usecases.save.flow.CreateFlowUseCase
import domain.models.flow.Flow
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.LayoutViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

internal const val DEFAULT_FLOW_NAME_PREFIX = "My Flow "

class NewFlowTitleViewModel @Inject constructor(
    streamFactory: StreamFactory,
    private val createFlowUseCase: CreateFlowUseCase,
    liveDataFactory: LiveDataFactory
) : LayoutViewModel<NewFlowTitleViewModel.Input, NewFlowTitleViewModel.Event>(
    "718adc21-f9c0",
    streamFactory,
    R.layout.fragment_new_flow_title
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var title: MutableLiveData<String> =
        liveDataFactory.mutableLiveData("c6c9cc9a-b9cd", "$DEFAULT_FLOW_NAME_PREFIX 1")
    var error: MutableLiveData<String> = liveDataFactory.mutableLiveData("ab7af821-67e7", "")

    fun onNext() {
        compositeDisposable += createFlowUseCase.invoke(CreateFlowInput(title.value))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handle(it.data)
                }
            }
    }

    private fun handle(data: Flow) {
        sendOutput(Event.OnNext(data))
    }

    sealed class Input

    sealed class Event {
        data class OnNext(val flow: Flow) : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
