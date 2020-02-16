package ui.newflow.createstep

import androidx.lifecycle.MutableLiveData
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import domain.flow.usecases.GetFlowByIdUseCase
import domain.models.flow.Flow
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class CreateStepViewModel @Inject constructor(
    streamFactory: StreamFactory,
    private val getFlowByIdUseCase: GetFlowByIdUseCase,
    private val liveDataFactory: LiveDataFactory
) : BaseViewModel<CreateStepViewModel.Input, CreateStepViewModel.Event>(
    "e2fc2772-418e",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val data: MutableLiveData<Any> = liveDataFactory.mutableLiveData("8e8cd2ab-2d38")
    val flow: MutableLiveData<Flow> = liveDataFactory.mutableLiveData("6fad0157-ba41")

    init {
        compositeDisposable += observeInput()
            .flatMap {
                when (it) {
                    is Input.FlowId -> getFlowByIdUseCase(it.id)
                }
            }
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetFlowByIdSuccess(it.data)
                }
            }
    }

    private fun handleGetFlowByIdSuccess(data: Flow) {
        flow.value = data
    }

    fun onShowSelectNode() {
        val flow = flow.value
        if (flow != null) {
            sendOutput(Event.OnShowSelectNode(flow))
        } else {
            sendOutput(Event.OnNoFlowError)
        }
    }

    sealed class Input {
        data class FlowId(val id: String) : Input()
    }

    sealed class Event {
        class OnShowSelectNode(val flow: Flow) : Event()
        object OnNoFlowError : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
