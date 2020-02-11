package ui.newflow.selectnode

import androidx.lifecycle.MutableLiveData
import core.lib.rxutils.plusAssign
import domain.core.result.Result
import domain.flow.models.Flow
import domain.flow.usecases.GetFlowByIdUseCase
import ui.lib.base.BaseViewModel
import ui.lib.utils.InputStream
import ui.lib.views.ListViewModel
import javax.inject.Inject

class NewFlowSelectNodeViewModel @Inject constructor(
    val nodeListViewModel: ListViewModel,
    private val getFlowByIdUseCase: GetFlowByIdUseCase
) : BaseViewModel<NewFlowSelectNodeViewModel.Event>() {

    val flowId: MutableLiveData<String> = MutableLiveData()
    val flowName: MutableLiveData<String> = MutableLiveData()

    private val flowIdStream = InputStream<String>()

    init {
        val items = listOf(
            SelectNodeItemViewModel(),
            SelectNodeItemViewModel(),
            SelectNodeItemViewModel(),
            SelectNodeItemViewModel(),
            SelectNodeItemViewModel(),
            SelectNodeItemViewModel()
        )

        nodeListViewModel.data.value = items

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