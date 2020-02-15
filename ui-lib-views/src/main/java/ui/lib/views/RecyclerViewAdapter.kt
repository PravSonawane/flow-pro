package ui.lib.views

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * A [RecyclerView.Adapter]
 */
class RecyclerViewAdapter : RecyclerView.Adapter<FeedViewHolder>() {

    private val adapterItems = ArrayList<ItemViewModel<Any, Any>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val item = adapterItems.find { it.viewType == viewType }
            ?: throw IllegalStateException("Unknown viewType: $viewType")
        return ItemViewHolder(parent, item.layoutId, item.variableId)
    }

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
    fun updateData(viewModels: List<ItemViewModel<Any, Any>>) {
        val newAdapterItemList = viewModels.map { it }
        val diffUtilResult = DiffUtil.calculateDiff(DiffUtilCallback(adapterItems, newAdapterItemList))
        this.adapterItems.clear()
        this.adapterItems.addAll(newAdapterItemList)
        diffUtilResult.dispatchUpdatesTo(this)
    }
}