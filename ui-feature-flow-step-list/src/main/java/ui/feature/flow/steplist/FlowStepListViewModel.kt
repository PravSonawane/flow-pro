package ui.feature.flow.steplist

import androidx.lifecycle.MutableLiveData
import core.lib.plugin.Plugin
import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import core.lib.usecase.ObservableResultUseCase
import core.lib.usecase.common.BusinessInput
import core.lib.usecase.common.BusinessUseCase
import domain.flow.usecases.GetAllStepsUseCase
import domain.flow.usecases.GetFlowByIdUseCase
import domain.models.flow.Flow
import domain.models.flow.Step
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject
import javax.inject.Named

class FlowStepListViewModel @Inject constructor(
    @Named("GET_FLOW_BY_ID") val getFlowByIdUseCase: BusinessUseCase<String, Flow>,
    private val getAllStepsUseCase: GetAllStepsUseCase,
    private val viewModelFactory: ViewModelFactory,
    private val streamFactory: StreamFactory,
    private val liveDataFactory: LiveDataFactory
) : BaseViewModel<FlowStepListViewModel.Input, FlowStepListViewModel.Event>(
    "ba45622c-d74a",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val items: MutableLiveData<List<FlowStepItemViewModel>> = liveDataFactory.mutableLiveData("b277b859-277a")
    val flowName: MutableLiveData<String> = liveDataFactory.mutableLiveData("a4efee98-acaa")

    init {

        compositeDisposable += observeInput()
            .flatMap {
                when (it) {
                    is Input.FlowId -> getAllStepsUseCase(GetAllStepsUseCase.Input(it.id))
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetAllStepsSuccess(it.data)
                    is Result.OnError -> handleGetAllNodesFailure(it.domainError)
                }
            }

        compositeDisposable += observeInput()
            .flatMap {
                when (it) {
                    is Input.FlowId -> getFlowByIdUseCase(
                        BusinessInput(
                            it.id,
                            "39de221c-2d52",
                            Plugin("8998e937-aa6d")
                        )
                    )
                }
            }
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetFlowByIdSuccess(it.data)
                }
            }
    }

    private fun handleGetAllStepsSuccess(steps: List<Step>) {
        val items: List<FlowStepItemViewModel> = steps.map {
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
        const val STEP_ITEM_ANALYTICS_KEY = "73d5b9c7-fbc4"
    }
}
