package ui.feature.flow.step.details

import androidx.lifecycle.MutableLiveData
import core.lib.plugin.Plugin
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import core.lib.usecase.common.BusinessData
import core.lib.usecase.common.BusinessUseCase
import domain.flow.usecases.GetFlowByIdUseCase
import domain.flow.usecases.GetInputStepsInput
import domain.flow.usecases.GetInputStepsUseCase
import domain.models.flow.Flow
import domain.models.flow.Step
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject
import javax.inject.Named

class StepDetailsViewModel @Inject constructor(
    @Named(GetFlowByIdUseCase.NAMED) val getFlowByIdUseCase: BusinessUseCase<String, Flow>,
    @Named(GetInputStepsUseCase.NAMED) val getInputStepsUseCase: BusinessUseCase<GetInputStepsInput, List<Step>>,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    val viewModelFactory: ViewModelFactory
) : BaseViewModel<StepDetailsViewModel.Input, StepDetailsViewModel.Event>(
    "ba45622c-d74a",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val items: MutableLiveData<List<StepDetailsItemViewModel>> =
        liveDataFactory.mutableLiveData("b277b859-277a")
    val flowName: MutableLiveData<String> = liveDataFactory.mutableLiveData("a4efee98-acaa")

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
                        "7880cff2-530e",
                        Plugin("152a67de-3676"),
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
        compositeDisposable += observeInput()
            .filter { it is Input.StepId }
            .map { it as Input.StepId }
            .flatMap {
                getInputStepsUseCase(
                    BusinessData(
                        "7880cff2-530e",
                        Plugin("152a67de-3676"),
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

    private fun handleGetFlowByIdSuccess(data: Flow) {
        flowName.value = data.name
    }

    private fun handleGetInputStepsSuccess(data: List<Step>) {
        items.value = data.map { viewModelFactory.create("a267d97f-d1e9", it) }
    }

    fun onNext() {
        sendOutput(Event.OnNewStep)
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
