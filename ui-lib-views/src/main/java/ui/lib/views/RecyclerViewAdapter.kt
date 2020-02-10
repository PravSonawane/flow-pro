package ui.lib.views

import androidx.annotation.VisibleForTesting
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ui.lib.base.BaseViewModel

/**
 * A [RecyclerView.Adapter]
 */
abstract class RecyclerViewAdapter : RecyclerView.Adapter<FeedViewHolder>() {

    private val adapterItems = ArrayList<FeedAdapterItem>()

    override fun onBindViewHolder(
        holder: FeedViewHolder,
        position: Int
    ) {
        holder.bind(adapterItems[holder.adapterPosition])
    }

    override fun getItemCount() = adapterItems.size

    override fun getItemViewType(position: Int) = adapterItems[position].viewType

    /**
     * Updates the data of this adapter.
     *
     * In case a specific item of this adapter needs to be updated or deleted or a new item needs to
     * be added, the [getData] function can be used to get the contents of the adapter. The [List]
     * returned by that function can be modified and passed as an argument to this function.
     *
     * Note that is it not required to call [notifyDataSetChanged] after calling this function. This
     * adapter handles the notification process internally. Calling [notifyDataSetChanged] or any
     * related methods might be inefficient.
     */
    fun updateData(listItemViewModels: List<BaseViewModel<Any>>) {
        val newAdapterItemList = listItemViewModels.map { FeedAdapterItem(getViewType(it), it) }
        val diffUtilResult = DiffUtil.calculateDiff(DiffUtilCallback(adapterItems, newAdapterItemList))
        this.adapterItems.clear()
        this.adapterItems.addAll(newAdapterItemList)
        diffUtilResult.dispatchUpdatesTo(this)
    }

    /**
     * Returns a defensive copy of this adapter's data.
     *
     * The returned [MutableList] can be modified (that is, add or delete items from it) and supplied
     * back to this adapter using its [updateData] function.
     */
    fun getData(): MutableList<BaseViewModel<*>> {
        return this.adapterItems.map { it.data }.toMutableList()
    }

    abstract fun getViewType(viewModel: BaseViewModel<*>): Int
}

abstract class FeedViewHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(feedAdapterItem: FeedAdapterItem)
}

/**
 * An item for the [RecyclerViewAdapter].
 *
 * An instance of this class has a [viewType] which is used by the
 * [RecyclerView.Adapter.onCreateViewHolder] function to identify the [RecyclerView.ViewHolder] to
 * be created
 */
data class FeedAdapterItem(
    val viewType: Int,
    val data: BaseViewModel<*>
)

/**
 * [DiffUtil.Callback] implementation for a [RecyclerView.Adapter] that renders [FeedAdapterItem]
 */
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
class DiffUtilCallback(
    private val oldList: List<FeedAdapterItem>,
    private val newList: List<FeedAdapterItem>
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