package ui.lib.views

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class FeedViewHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(itemViewModel: ItemViewModel<*, *>)
}

/**
 * [DiffUtil.Callback] implementation for a [RecyclerView.Adapter] that renders [ItemViewModel]
 */
class DiffUtilCallback(
    private val oldList: List<ItemViewModel<*, *>>,
    private val newList: List<ItemViewModel<*, *>>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
