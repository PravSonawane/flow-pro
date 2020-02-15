package ui.lib.views

import androidx.annotation.VisibleForTesting
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class FeedViewHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(itemViewModel: ItemViewModel<Any, Any>)
}

/**
 * [DiffUtil.Callback] implementation for a [RecyclerView.Adapter] that renders [AdapterItem]
 */
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
class DiffUtilCallback(
    private val oldList: List<ItemViewModel<Any, Any>>,
    private val newList: List<ItemViewModel<Any, Any>>
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