package ui.newflow.selectnode

import androidx.lifecycle.LiveData
import domain.models.flow.Node
import ui.feature.create.newflow.BR
import ui.feature.create.newflow.R
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel

const val VIEW_TYPE_ITEM_NODE = 100000

class SelectNodeItemViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    item: Node
) : ItemViewModel<SelectNodeItemViewModel.Input, SelectNodeItemViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.list_item_select_node,
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
