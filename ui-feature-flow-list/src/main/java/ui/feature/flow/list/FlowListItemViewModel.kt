package ui.feature.flow.list

import androidx.lifecycle.LiveData
import domain.models.flow.Flow
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel
import ui.lib.views.list.ListViewModel

class FlowListItemViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    item: Flow
) : ItemViewModel<ListViewModel.ItemInput, ListViewModel.ItemOutput>(
    analyticsKey,
    streamFactory,
    R.layout.list_item_flow,
    BR.viewModel
) {

    val flow: LiveData<Flow> = liveDataFactory.liveData("e93b6b24-7e7f", item)

    sealed class Input : ListViewModel.ItemInput
    sealed class Event : ListViewModel.ItemOutput {
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
