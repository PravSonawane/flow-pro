package ui.feature.flow.selectstep

import androidx.lifecycle.LiveData
import domain.models.flow.Node
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.base.ItemViewModel

class SelectStepItemViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    item: Node
) : ItemViewModel<SelectStepItemViewModel.Input, SelectStepItemViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.list_item_select_step,
    BR.viewModel
) {

    val node: LiveData<Node> = liveDataFactory.liveData("0c19c42b-4321", item)

    sealed class Input
    sealed class Event

    override fun toString(): String {
        return "SelectStepItemViewModel(node=$node)"
    }
}
