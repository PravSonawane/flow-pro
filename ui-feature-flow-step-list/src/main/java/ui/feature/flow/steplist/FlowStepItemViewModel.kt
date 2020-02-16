package ui.feature.flow.steplist

import androidx.lifecycle.LiveData
import domain.models.flow.Node
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel

const val VIEW_TYPE_ITEM_NODE = 100000

class FlowStepItemViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    item: Node
) : ItemViewModel<FlowStepItemViewModel.Input, FlowStepItemViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.list_item_flow_step,
    BR.viewModel,
    VIEW_TYPE_ITEM_NODE
) {

    val node: LiveData<Node> = liveDataFactory.liveData("6a4b42fe-09a3", item)

    sealed class Input
    sealed class Event

    override fun toString(): String {
        return "SelectNodeItemViewModel(node=$node)"
    }
}
