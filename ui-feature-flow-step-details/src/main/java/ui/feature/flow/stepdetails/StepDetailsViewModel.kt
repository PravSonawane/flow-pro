package ui.feature.flow.stepdetails

import core.lib.plugin.Plugin
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import core.lib.usecase.ObservableResultUseCase
import core.lib.usecase.common.BusinessData
import core.lib.usecase.common.BusinessUseCase
import domain.flow.usecases.GetCurrentInputStepsUseCase
import domain.flow.usecases.GetCurrentOutputStepsUseCase
import domain.flow.usecases.GetFlowByIdUseCase
import domain.flow.usecases.GetInputStepsInput
import domain.flow.usecases.GetOutputStepsInput
import domain.flow.usecases.GetStepByIdUseCase
import domain.models.flow.Flow
import domain.models.flow.Step
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.LayoutViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel
import ui.lib.views.list.ListViewModel
import ui.lib.views.list.ListViewModelFactory
import javax.inject.Inject
import javax.inject.Named

class StepDetailsViewModel @Inject constructor(
    @Named(GetStepByIdUseCase.NAMED)
    val getStepByIdUseCase: BusinessUseCase<String, Step>,
    @Named(GetStepByIdUseCase.NAMED_V2)
    val getStepByIdUseCaseV2: ObservableResultUseCase<String, Step>,
    val getFlowByIdUseCase: GetFlowByIdUseCase,
    @Named(GetCurrentInputStepsUseCase.NAMED)
    val getCurrentInputStepsUseCase: ObservableResultUseCase<GetInputStepsInput, List<Step>>,
    @Named(GetCurrentOutputStepsUseCase.NAMED)
    val getCurrentOutputStepsUseCase: ObservableResultUseCase<GetOutputStepsInput, List<Step>>,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    listViewModelFactory: ListViewModelFactory,
    private val viewModelFactory: ViewModelFactory
) : LayoutViewModel<StepDetailsViewModel.Input, StepDetailsViewModel.Event>(
    "29e6fff3-32a7",
    streamFactory,
    R.layout.fragment_flow_step_details
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val inputListViewModel =
        listViewModelFactory.create<ItemViewModel<ListViewModel.ItemInput, ListViewModel.ItemOutput>>(
            "d03bbb54-bfe0"
        )
    val outputListViewModel =
        listViewModelFactory.create<ItemViewModel<ListViewModel.ItemInput, ListViewModel.ItemOutput>>(
            "0f2ad39e-e31c"
        )
    val flow = liveDataFactory.mutableLiveData<Flow>("1692a7e5-47f5")
    val step = liveDataFactory.mutableLiveData<Step>("53969ce5-8ef9")

    init {
        handleFlowIdInput()
        handleStepIdInput()

        compositeDisposable += inputListViewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { event ->
                when (event) {
                    is StepDetailsItemViewModel.Event.OnStepDetails -> handleOnStepDetails(event.step)
                    is AddStepItemViewModel.Event.OnAddMore -> handleOnAddInputStep(event.step)
                }
            }

        compositeDisposable += outputListViewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { event ->
                when (event) {
                    is StepDetailsItemViewModel.Event.OnStepDetails -> handleOnStepDetails(event.step)
                    is AddStepItemViewModel.Event.OnAddMore -> handleOnAddOutputStep(event.step)
                }
            }
    }

    private fun handleFlowIdInput() {
        compositeDisposable += observeInput()
            .filter { it is Input.FlowId }
            .map { it as Input.FlowId }
            .flatMap {
                getFlowByIdUseCase(
                    BusinessData(
                        "e0ba73ff-5f29",
                        Plugin("f34d4c75-3d81"),
                        it.id
                    )
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetFlowByIdSuccess(it.data)
                }
            }
    }

    private fun handleStepIdInput() {
        getStep()
        getInputSteps()
        getOutputSteps()
    }

    private fun getStep() {
        compositeDisposable += observeInput()
            .filter { it is Input.StepId }
            .map { it as Input.StepId }
            .flatMap { getStepByIdUseCaseV2(it.id) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetStepByIdSuccess(it.data)
                }
            }
    }

    private fun getInputSteps() {
        compositeDisposable += observeInput()
            .filter { it is Input.StepId }
            .map { it as Input.StepId }
            .flatMap { getCurrentInputStepsUseCase(GetInputStepsInput(it.id)) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetInputStepsSuccess(it.data)
                }
            }
    }

    private fun getOutputSteps() {
        compositeDisposable += observeInput()
            .filter { it is Input.StepId }
            .map { it as Input.StepId }
            .flatMap { getCurrentOutputStepsUseCase(GetOutputStepsInput(it.id)) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetOutputStepsSuccess(it.data)
                }
            }
    }

    private fun handleOnStepDetails(step: Step) {
        sendOutput(Event.OnStepDetails(step))
    }

    private fun handleOnAddInputStep(step: Step) {
        flow.value?.let { sendOutput(Event.OnAddInputStep(it, step)) }
    }

    private fun handleOnAddOutputStep(step: Step) {
        flow.value?.let { sendOutput(Event.OnAddOutputStep(it, step)) }
    }

    private fun handleGetFlowByIdSuccess(data: Flow) {
        flow.value = data
    }

    private fun handleGetStepByIdSuccess(data: Step) {
        step.value = data
    }

    private fun handleGetInputStepsSuccess(data: List<Step>) {
        val viewModels: MutableList<ItemViewModel<ListViewModel.ItemInput, ListViewModel.ItemOutput>> =
            data.map { viewModelFactory.create("b3c872df-8066", it) }.toMutableList()

        step.value?.let { viewModels.add(viewModelFactory.createAddStep("be0cc22a-df00", it)) }
        inputListViewModel.sendInput(ListViewModel.Input(viewModels))
    }

    private fun handleGetOutputStepsSuccess(data: List<Step>) {
        val viewModels: MutableList<ItemViewModel<ListViewModel.ItemInput, ListViewModel.ItemOutput>> =
            data.map { viewModelFactory.create("08a354bd-ab75", it) }.toMutableList()
        step.value?.let { viewModels.add(viewModelFactory.createAddStep("79906fc2-9f40", it)) }
        outputListViewModel.sendInput(ListViewModel.Input(viewModels))
    }

    sealed class Input {
        data class FlowId(val id: String) : Input()
        data class StepId(val id: String) : Input()
    }

    sealed class Event {
        object OnNewStep : Event()
        data class OnStepDetails(val step: Step) : Event()
        data class OnAddInputStep(val flow: Flow, val step: Step) : Event()
        data class OnAddOutputStep(val flow: Flow, val step: Step) : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
