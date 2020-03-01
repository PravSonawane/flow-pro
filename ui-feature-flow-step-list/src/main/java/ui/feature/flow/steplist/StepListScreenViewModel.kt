package ui.feature.flow.steplist

import androidx.lifecycle.MutableLiveData
import core.lib.rxutils.plusAssign
import domain.models.flow.Step
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class StepListScreenViewModel @Inject constructor(
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    val incompleteInputViewModel: IncompleteInputViewModel,
    val inputStepListViewModel: InputStepListViewModel
) : BaseViewModel<StepListScreenViewModel.Input, StepListScreenViewModel.Event>(
    "493db3c5-f5ff",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val screenMode: MutableLiveData<ScreenMode> = liveDataFactory.mutableLiveData("7f0d3a0d-8d76")
    val stepType: MutableLiveData<StepType> = liveDataFactory.mutableLiveData("2fe0342c-9b15")

    init {
        compositeDisposable += observeInput()
            .subscribe { handleInputStepList(it) }
    }

    private fun handleInputStepList(input: Input) {
        if (input.flowId == null) {
            screenMode.value = ScreenMode.INCOMPLETE_INPUT
            incompleteInputViewModel.sendInput(IncompleteInputViewModel.Input("Flow ID is required"))
        } else {
            if (input.stepId != null) {
                stepType.value = StepType.INPUT
                inputStepListViewModel.sendInput(InputStepListViewModel.Input(input.flowId, input.stepId))
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

    enum class StepType {
        /** List all [Step]s */
        ALL,
        /** List input [Step]s */
        INPUT,
        /** List output [Step]s */
        OUTPUT
    }

    data class Input(
        val flowId: String? = null,
        val stepId: String? = null,
        val stepType: StepType? = StepType.ALL
    )

    sealed class Event {
        object OnNewStep : Event()
        data class OnViewStep(val flowId: String, val step: Step) : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
