package ui.feature.flow.steplist

import androidx.lifecycle.MutableLiveData
import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import domain.flow.usecases.GetAllNodesUseCase
import domain.flow.usecases.GetFlowByIdUseCase
import domain.models.flow.Flow
import domain.models.flow.Node
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class FlowStepListViewModel @Inject constructor(
    private val getFlowByIdUseCase: GetFlowByIdUseCase,
    private val getAllNodesUseCase: GetAllNodesUseCase,
    private val viewModelFactory: ViewModelFactory,
    private val streamFactory: StreamFactory,
    private val liveDataFactory: LiveDataFactory
) : BaseViewModel<FlowStepListViewModel.Input, FlowStepListViewModel.Event>(
    "b7dcd411-0058",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val items: MutableLiveData<List<FlowStepItemViewModel>> = liveDataFactory.mutableLiveData("e588c436-e1a8")
    val flowName: MutableLiveData<String> = liveDataFactory.mutableLiveData("3fbb7441-da29")

    init {

        compositeDisposable += getAllNodesUseCase(Unit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetAllNodesSuccess(it.data)
                    is Result.OnError -> handleGetAllNodesFailure(it.domainError)
                }
            }

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

    private fun handleGetAllNodesSuccess(nodes: List<Node>) {
        val items: List<FlowStepItemViewModel> = nodes.map {
            viewModelFactory.create(NODE_ITEM_ANALYTICS_KEY, it)
        }
        this.items.value = items
    }

    private fun handleGetAllNodesFailure(error: DomainError) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    private fun handleGetFlowByIdSuccess(data: Flow) {
        flowName.value = data.name
    }

    fun onNext() {
        sendOutput(Event.OnNewStep)
    }

    sealed class Input {
        class FlowId(val id: String) : Input()
    }

    sealed class Event {
        object OnNewStep : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    companion object {
        const val NODE_ITEM_ANALYTICS_KEY = "528a3a76-6aa9"
    }
}
