package ui.newflow.selectnode

import ui.feature.create.newflow.BR
import ui.feature.create.newflow.R
import ui.lib.views.ItemViewModel

const val VIEW_TYPE_ITEM_NODE = 100000

class SelectNodeItemViewModel : ItemViewModel<SelectNodeItemViewModel.Event>(
    R.layout.list_item_select_node,
    BR.viewModel,
    VIEW_TYPE_ITEM_NODE
) {
    sealed class Event
}