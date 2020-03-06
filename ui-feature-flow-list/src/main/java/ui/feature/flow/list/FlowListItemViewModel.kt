package ui.feature.flow.list

import androidx.lifecycle.LiveData
import domain.models.flow.Flow
import domain.models.flow.Step
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel

class FlowListItemViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    item: Flow
) : ItemViewModel<FlowListItemViewModel.Input, FlowListItemViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.list_item_flow,
    BR.viewModel,
    R.layout.list_item_flow
) {

    val flow: LiveData<Flow> = liveDataFactory.liveData("e93b6b24-7e7f", item)

    sealed class Input
    sealed class Event {
        data class OnViewFlow(val flow: Flow) : Event()
    }

    fun onViewFlow() {
        flow.value?.let { sendOutput(
            Event.OnViewFlow(
                it
            )
        ) }
    }

    override fun toString(): String {
        return "FlowListItemViewModel(flow=$flow)"
    }
}
