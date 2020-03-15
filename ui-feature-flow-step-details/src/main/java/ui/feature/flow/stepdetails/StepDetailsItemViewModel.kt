package ui.feature.flow.stepdetails

import androidx.lifecycle.LiveData
import domain.models.flow.Step
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel
import ui.lib.views.list.ListViewModel

class StepDetailsItemViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    item: Step
) : ItemViewModel<ListViewModel.ItemInput, ListViewModel.ItemOutput>(
    analyticsKey,
    streamFactory,
    R.layout.list_item_step_details,
    BR.viewModel
) {

    val step: LiveData<Step> = liveDataFactory.liveData("d2e71ceb-80b4", item)

    sealed class Input : ListViewModel.ItemInput
    sealed class Event : ListViewModel.ItemOutput {
        data class OnStepDetails(val step: Step) : Event()
    }

    fun onStepDetails() {
        step.value?.let { sendOutput(
            Event.OnStepDetails(
                it
            )
        ) }
    }

    override fun toString(): String {
        return "FlowStepItemViewModel(step=$step)"
    }
}
