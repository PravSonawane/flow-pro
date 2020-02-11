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