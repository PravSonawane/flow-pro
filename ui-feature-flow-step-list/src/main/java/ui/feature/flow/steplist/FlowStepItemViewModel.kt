package ui.feature.flow.steplist

import androidx.lifecycle.LiveData
import domain.models.flow.Step
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel

class FlowStepItemViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    item: Step
) : ItemViewModel<FlowStepItemViewModel.Input, FlowStepItemViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.list_item_flow_step,
    BR.viewModel,
    100000
) {

    val step: LiveData<Step> = liveDataFactory.liveData("61b38fc7-69c5", item)

    fun onSelectStep() {
        step.value?.let { sendOutput(Event.OnSelectStep(it)) }
    }

    sealed class Input
    sealed class Event {
        data class OnSelectStep(val step: Step) : Event()
    }

    override fun toString(): String {
        return "FlowStepItemViewModel(step=$step)"
    }
}
