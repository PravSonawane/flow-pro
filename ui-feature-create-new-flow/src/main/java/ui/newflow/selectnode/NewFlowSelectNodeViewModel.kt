package ui.newflow.selectnode

import androidx.lifecycle.MutableLiveData
import core.lib.rxutils.plusAssign
import domain.core.Result
import domain.flow.models.Flow
import domain.flow.usecases.GetFlowByIdUseCase
import ui.lib.base.BaseViewModel
import ui.lib.utils.InputStream
import javax.inject.Inject

class NewFlowSelectNodeViewModel @Inject constructor(
    private val getFlowByIdUseCase: GetFlowByIdUseCase
) : BaseViewModel<NewFlowSelectNodeViewModel.Event>() {

    val flowId: MutableLiveData<String> = MutableLiveData()
    val flowName: MutableLiveData<String> = MutableLiveData()
    var error: String = ""

    private val flowIdStream = InputStream<String>()

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
        flowName.value = data.name
    }

    fun onNext() {
        eventStream.onNext(Event.OnNext)
    }

    sealed class Event {
        object OnNext : Event()
    }
}