package ui.feature.flow.selectnode

import androidx.lifecycle.LiveData
import domain.models.flow.Node
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.base.ItemViewModel

class SelectNodeItemViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    item: Node
) : ItemViewModel<SelectNodeItemViewModel.Input, SelectNodeItemViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.list_item_select_node,
    BR.viewModel
) {

    val node: LiveData<Node> = liveDataFactory.liveData("6a4b42fe-09a3", item)

    sealed class Input
    sealed class Event

    override fun toString(): String {
        return "SelectNodeItemViewModel(node=$node)"
    }
}
