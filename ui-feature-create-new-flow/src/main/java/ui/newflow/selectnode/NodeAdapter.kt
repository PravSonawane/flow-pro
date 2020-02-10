package ui.newflow.selectnode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ui.feature.create_new_flow.R
import ui.feature.create_new_flow.databinding.ListItemSelectNodeBinding
import ui.lib.base.BaseViewModel
import ui.lib.views.FeedAdapterItem
import ui.lib.views.FeedViewHolder
import ui.lib.views.RecyclerViewAdapter
import java.lang.IllegalArgumentException

class NodeAdapter : RecyclerViewAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM_NODE -> ItemNodeViewHolder(parent)
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

private class ItemNodeViewHolder(parent: ViewGroup) : FeedViewHolder(inflate(parent)) {

    override fun bind(feedAdapterItem: FeedAdapterItem) {
        (binding as ListItemSelectNodeBinding).viewModel =
            feedAdapterItem.data as? SelectNodeItemViewModel
    }

    companion object {
        /** Inflates the view associated with this [RecyclerView.ViewHolder] */
        private fun inflate(parent: ViewGroup?): ViewDataBinding {
            return DataBindingUtil.inflate<ListItemSelectNodeBinding>(
                LayoutInflater.from(parent?.context), R.layout.list_item_select_node, parent, false
            )
        }
    }
}

private const val VIEW_TYPE_ITEM_NODE = 100000