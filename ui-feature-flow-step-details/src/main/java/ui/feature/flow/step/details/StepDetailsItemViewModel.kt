package ui.feature.flow.step.details

import androidx.lifecycle.LiveData
import domain.models.flow.Step
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel

const val VIEW_TYPE_ITEM_NODE = 100000

class StepDetailsItemViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    item: Step
) : ItemViewModel<StepDetailsItemViewModel.Input, StepDetailsItemViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.list_item_step_details,
    BR.viewModel,
    VIEW_TYPE_ITEM_NODE
) {

    val step: LiveData<Step> = liveDataFactory.liveData("d2e71ceb-80b4", item)

    sealed class Input
    sealed class Event

    override fun toString(): String {
        return "FlowStepItemViewModel(step=$step)"
    }
}
