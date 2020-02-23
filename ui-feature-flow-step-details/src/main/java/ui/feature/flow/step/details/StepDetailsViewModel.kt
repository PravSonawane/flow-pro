package ui.feature.flow.step.details

import androidx.lifecycle.MutableLiveData
import core.lib.plugin.Plugin
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import core.lib.usecase.common.BusinessData
import core.lib.usecase.common.BusinessUseCase
import domain.flow.usecases.*
import domain.models.flow.Flow
import domain.models.flow.Step
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject
import javax.inject.Named

class StepDetailsViewModel @Inject constructor(
    @Named(GetStepByIdUseCase.NAMED) val getStepByIdUseCase: BusinessUseCase<String, Step>,
    @Named(GetFlowByIdUseCase.NAMED) val getFlowByIdUseCase: BusinessUseCase<String, Flow>,
    @Named(GetInputStepsUseCase.NAMED) val getInputStepsUseCase: BusinessUseCase<GetInputStepsInput, List<Step>>,
    @Named(GetOutputStepsUseCase.NAMED) val getOutputStepsUseCase: BusinessUseCase<GetOutputStepsInput, List<Step>>,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    val viewModelFactory: ViewModelFactory
) : BaseViewModel<StepDetailsViewModel.Input, StepDetailsViewModel.Event>(
    "29e6fff3-32a7",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val inputItems: MutableLiveData<List<StepDetailsItemViewModel>> =
        liveDataFactory.mutableLiveData("d03bbb54-bfe0")
    val outputItems: MutableLiveData<List<StepDetailsItemViewModel>> =
        liveDataFactory.mutableLiveData("0f2ad39e-e31c")
    val flowName: MutableLiveData<String> = liveDataFactory.mutableLiveData("1692a7e5-47f5")
    val step: MutableLiveData<Step> = liveDataFactory.mutableLiveData("53969ce5-8ef9")

    init {
        handleFlowIdInput()
        handleStepIdInput()
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
            .flatMap {
                getStepByIdUseCase(
                    BusinessData(
                        "98301cab-9995",
                        Plugin("8a35d450-f99b"),
                        it.id
                    )
                )
            }
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
            .flatMap {
                getInputStepsUseCase(
                    BusinessData(
                        "52ad24ac-e785",
                        Plugin("0290f8da-b3e3"),
                        GetInputStepsInput(it.id)
                    )
                )
            }
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
            .flatMap {
                getOutputStepsUseCase(
                    BusinessData(
                        "dfbb9ed3-588b",
                        Plugin("2b100786-074c"),
                        GetOutputStepsInput(it.id)
                    )
                )
            }
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetOutputStepsSuccess(it.data)
                }
            }
    }

    private fun handleGetFlowByIdSuccess(data: Flow) {
        flowName.value = data.name
    }

    private fun handleGetStepByIdSuccess(data: Step) {
        step.value = data
    }

    private fun handleGetInputStepsSuccess(data: List<Step>) {
        inputItems.value = data.map { viewModelFactory.create("b3c872df-8066", it) }
    }

    private fun handleGetOutputStepsSuccess(data: List<Step>) {
        outputItems.value = data.map { viewModelFactory.create("08a354bd-ab75", it) }
    }

    sealed class Input {
        data class FlowId(val id: String) : Input()
        data class StepId(val id: String) : Input()
    }

    sealed class Event {
        object OnNewStep : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
