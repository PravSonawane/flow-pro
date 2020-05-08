package ui.feature.flow.selectstep

import androidx.lifecycle.MutableLiveData
import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import core.lib.usecase.ObservableResultUseCase
import domain.flow.usecases.get.flow.GetFlowByIdUseCase
import domain.flow.usecases.get.node.GetAllNodesUseCase
import domain.models.flow.Flow
import domain.models.flow.Node
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.LayoutViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject
import javax.inject.Named

class SelectStepViewModel @Inject constructor(
    @Named(GetFlowByIdUseCase.NAMED)
    getFlowByIdUseCase: ObservableResultUseCase<String, Flow>,
    getAllNodesUseCase: GetAllNodesUseCase,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    private val viewModelFactory: ViewModelFactory
) : LayoutViewModel<SelectStepViewModel.Input, SelectStepViewModel.Event>(
    "50ef9e44-a305",
    streamFactory,
    R.layout.fragment_flow_select_step
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val items: MutableLiveData<List<SelectStepItemViewModel>> = liveDataFactory.mutableLiveData("aee8f067-4962")
    val flowName: MutableLiveData<String> = liveDataFactory.mutableLiveData("0bdc8691-68f9")

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
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetFlowByIdSuccess(it.data)
                }
            }
    }

    private fun handleGetAllNodesSuccess(nodes: List<Node>) {
        val items: List<SelectStepItemViewModel> = nodes.map {
            viewModelFactory.create(STEP_ITEM_ANALYTICS_KEY, it)
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
        sendOutput(Event.OnNext)
    }

    sealed class Input {
        data class FlowId(val id: String) : Input()
    }

    sealed class Event {
        object OnNext : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    companion object {
        const val STEP_ITEM_ANALYTICS_KEY = "45eafd72-9a78"
    }
}
