package ui.newflow.selectnode

import androidx.lifecycle.MutableLiveData
import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import domain.flow.usecases.GetAllNodesUseCase
import domain.flow.usecases.GetFlowByIdUseCase
import domain.models.flow.Flow
import domain.models.flow.Node
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.lib.base.BaseViewModel
import ui.lib.utils.InputStream
import javax.inject.Inject

class NewFlowSelectNodeViewModel @Inject constructor(
    private val getFlowByIdUseCase: GetFlowByIdUseCase,
    private val getAllNodesUseCase: GetAllNodesUseCase,
    private val viewModelFactory: ViewModelFactory
) : BaseViewModel<NewFlowSelectNodeViewModel.Event>() {

    val data: MutableLiveData<Any> = MutableLiveData()
    val flowId: MutableLiveData<String> = MutableLiveData()
    val flowName: MutableLiveData<String> = MutableLiveData()

    private val flowIdStream = InputStream<String>()

    init {

        compositeDisposable += getAllNodesUseCase(Unit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetAllNodesSuccess(it.data)
                    is Result.OnError -> handleGetAllNodesFailure(it.domainError)
                }
            }

        flowId.observeForever { flowIdStream.publish(it) }

        compositeDisposable += flowIdStream.subscribe()
            .flatMap { getFlowByIdUseCase(it) }
            .subscribe {
                when(it) {
                    is Result.OnSuccess -> handleGetFlowByIdSuccess(it.data)
                }
            }
    }

    private fun handleGetAllNodesSuccess(nodes: List<Node>) {
       val items: List<SelectNodeItemViewModel> = nodes.map { viewModelFactory.create(it) }
        data.value = items
    }

    private fun handleGetAllNodesFailure(error: DomainError) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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