package ui.newflow.selectnode

import android.view.ViewGroup
import ui.feature.create_new_flow.BR
import ui.feature.create_new_flow.R
import ui.lib.base.BaseViewModel
import ui.lib.views.FeedViewHolder
import ui.lib.views.ItemViewHolder
import ui.lib.views.RecyclerViewAdapter

class NodeAdapter : RecyclerViewAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM_NODE -> ItemViewHolder(parent, R.layout.list_item_select_node, BR.viewModel)
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun getViewType(viewModel: BaseViewModel<*>): Int {
        return when (viewModel) {
            is SelectNodeItemViewModel -> VIEW_TYPE_ITEM_NODE
            else -> throw IllegalArgumentException("Unknown view model: $viewModel")
        }
    }
}

private const val VIEW_TYPE_ITEM_NODE = 100000