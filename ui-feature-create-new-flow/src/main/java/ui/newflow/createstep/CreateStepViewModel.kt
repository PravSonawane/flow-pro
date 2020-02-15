package ui.newflow.createstep

import androidx.lifecycle.MutableLiveData
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import domain.flow.usecases.GetFlowByIdUseCase
import domain.models.flow.Flow
import ui.lib.base.BaseViewModel
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class CreateStepViewModel @Inject constructor(
    private val getFlowByIdUseCase: GetFlowByIdUseCase
) : BaseViewModel<CreateStepViewModel.Event>() {

    val data: MutableLiveData<Any> = MutableLiveData()
    val flowId: MutableLiveData<String> = MutableLiveData()
    val flow: MutableLiveData<Flow> = MutableLiveData()

    private val flowIdStream = StreamFactory().simpleStream<String>()

    init {

        flowId.observeForever { flowIdStream.publish(it) }

        compositeDisposable += flowIdStream.subscribe()
            .flatMap { getFlowByIdUseCase(it) }
            .subscribe {
                when(it) {
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
            eventStream.onNext(Event.OnShowSelectNode(flow))
        } else {
            eventStream.onNext(Event.OnNoFlowError)
        }
    }

    sealed class Event {
        class OnShowSelectNode(val flow: Flow) : Event()
        object OnNoFlowError : Event()
    }
}