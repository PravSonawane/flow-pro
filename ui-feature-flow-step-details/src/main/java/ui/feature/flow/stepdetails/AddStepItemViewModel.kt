package ui.feature.flow.stepdetails

import domain.models.flow.Step
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel
import ui.lib.views.list.ListViewModel

class AddStepItemViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    private val item: Step
) : ItemViewModel<ListViewModel.ItemInput, ListViewModel.ItemOutput>(
    analyticsKey,
    streamFactory,
    R.layout.list_item_add_step,
    BR.viewModel
) {

    sealed class Input : ListViewModel.ItemInput
    sealed class Event : ListViewModel.ItemOutput {
        data class OnAddMore(val step: Step) : Event()
    }

    fun onAddMore() {
        sendOutput(Event.OnAddMore(item))
    }

    override fun toString(): String {
        return "AddStepItemViewModel(step=$item)"
    }
}
