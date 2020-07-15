package ui.lib.databinding.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import core.lib.rxutils.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ui.lib.base.ItemViewModel

/**
 * A [RecyclerView.Adapter]
 */
class RecyclerViewAdapter : RecyclerView.Adapter<FeedViewHolder>() {

    private val compositeDisposable = CompositeDisposable()
    private val adapterItems = ArrayList<ItemViewModel<*, *>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val item = adapterItems.find { it.layoutId == viewType }
            ?: throw IllegalStateException("Unknown viewType (layout resource ID): $viewType")
        return ui.lib.databinding.adapters.ItemViewHolder(
            parent,
            item.layoutId,
            item.variableId
        )
    }

    override fun onBindViewHolder(
        holder: FeedViewHolder,
        position: Int
    ) {
        holder.bind(adapterItems[holder.adapterPosition])
    }

    override fun getItemCount() = adapterItems.size

    override fun getItemViewType(position: Int) = adapterItems[position].layoutId

    /**
     * Updates the data of this adapter.
     *
     * Note that is it not required to call [notifyDataSetChanged] after calling this function. This
     * adapter handles the notification process internally. Calling [notifyDataSetChanged] or any
     * related methods might be inefficient.
     */
    fun updateData(viewModels: List<ItemViewModel<*, *>>) {
        compositeDisposable += Observable.fromCallable {
            val newAdapterItemList = viewModels.map { it }
            val result = DiffUtil.calculateDiff(
                DiffUtilCallback(
                    adapterItems,
                    newAdapterItemList
                )
            )
            return@fromCallable Pair(newAdapterItemList, result)
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.adapterItems.clear()
                this.adapterItems.addAll(it.first)
                it.second.dispatchUpdatesTo(this)
            }
    }

    override fun onViewDetachedFromWindow(holder: FeedViewHolder) {
        compositeDisposable.clear()
        super.onViewDetachedFromWindow(holder)
    }
}
