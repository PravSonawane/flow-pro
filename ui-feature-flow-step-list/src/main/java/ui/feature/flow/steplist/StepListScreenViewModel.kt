package ui.feature.flow.steplist

import androidx.lifecycle.MutableLiveData
import core.lib.rxutils.plusAssign
import domain.models.flow.Step
import domain.models.flow.StepType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.LayoutViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class StepListScreenViewModel @Inject constructor(
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    val allStepListViewModel: StepListViewModel,
    val incompleteInputViewModel: IncompleteInputViewModel,
    val inputStepListViewModel: StepListViewModel,
    val outputStepListViewModel: StepListViewModel
) : LayoutViewModel<StepListScreenViewModel.Input, StepListScreenViewModel.Event>(
    "493db3c5-f5ff",
    streamFactory,
    R.layout.fragment_step_list
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val screenMode: MutableLiveData<ScreenMode> = liveDataFactory.mutableLiveData("7f0d3a0d-8d76")

    init {
        compositeDisposable += observeInput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleInputStepList(it) }

        compositeDisposable += allStepListViewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleAllStepListOutputs(it) }
    }

    private fun handleAllStepListOutputs(event: StepListViewModel.Event) {
        when (event) {
            is StepListViewModel.Event.OnViewStep -> sendOutput(
                Event.OnViewStep(
                    event.flowId,
                    event.step
                )
            )
        }
    }

    private fun handleInputStepList(input: Input) {
        when {
            input.flowId == null -> {
                screenMode.value = ScreenMode.INCOMPLETE_INPUT
                incompleteInputViewModel.sendInput(IncompleteInputViewModel.Input("Flow ID is required"))
            }
            input.stepId == null && input.stepType == null -> {
                screenMode.value = ScreenMode.ALL_STEPS
                allStepListViewModel.sendInput(StepListViewModel.Input(input.flowId))
            }
            input.stepId != null && input.stepType == StepType.INPUT -> {
                screenMode.value = ScreenMode.INPUT_STEPS
                inputStepListViewModel.sendInput(
                    StepListViewModel.Input(
                        input.flowId,
                        input.stepId
                    )
                )
            }
            input.stepId != null && input.stepType == StepType.OUTPUT -> {
                screenMode.value = ScreenMode.OUTPUT_STEPS
                inputStepListViewModel.sendInput(
                    StepListViewModel.Input(
                        input.flowId,
                        input.stepId
                    )
                )
            }
            else -> {
                screenMode.value = ScreenMode.INCOMPLETE_INPUT
                incompleteInputViewModel.sendInput(IncompleteInputViewModel.Input("Unknown case"))
            }
        }
    }

    enum class ScreenMode {
        LOADING,
        ERROR,
        INCOMPLETE_INPUT,
        ALL_STEPS,
        INPUT_STEPS,
        OUTPUT_STEPS
    }

    data class Input(
        val flowId: String? = null,
        val stepId: String? = null,
        val stepType: StepType? = null
    )

    sealed class Event {
        data class OnViewStep(val flowId: String, val step: Step) : Event()
    }

    override fun onCleared() {
        allStepListViewModel.onCleared()
        incompleteInputViewModel.onCleared()
        inputStepListViewModel.onCleared()
        compositeDisposable.dispose()
    }
}
