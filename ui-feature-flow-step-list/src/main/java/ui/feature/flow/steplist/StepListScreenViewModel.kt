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
    val inputStepListViewModel: InputStepListViewModel
) : BaseViewModel<StepListScreenViewModel.Input, StepListScreenViewModel.Event>(
    "493db3c5-f5ff",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val screenMode: MutableLiveData<ScreenMode> = liveDataFactory.mutableLiveData("2fe0342c-9b15")

    init {
        compositeDisposable += observeInput()
            .subscribe {
                when (it) {
                    is Input.InputStepList -> handleInputStepList(it)
                }
            }
    }

    private fun handleInputStepList(input: Input.InputStepList) {
        screenMode.value = ScreenMode.INPUT_STEP_LIST
        inputStepListViewModel.sendInput(InputStepListViewModel.Input(input.flowId, input.stepId))
    }

    enum class ScreenMode {
        INPUT_STEP_LIST,
        OUTPUT_STEP_LIST
    }

    sealed class Input {
        data class InputStepList(val flowId: String, val stepId: String) : Input()
    }

    sealed class Event {
        object OnNewStep : Event()
        data class OnViewStep(val flowId: String, val step: Step) : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
