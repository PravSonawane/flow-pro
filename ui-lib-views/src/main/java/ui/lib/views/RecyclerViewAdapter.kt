package ui.lib.views

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import core.lib.rxutils.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * A [RecyclerView.Adapter]
 */
class RecyclerViewAdapter : RecyclerView.Adapter<FeedViewHolder>() {

    private val compositeDisposable = CompositeDisposable()
    private val adapterItems = ArrayList<ItemViewModel<*, *>>()

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
    fun updateData(viewModels: List<ItemViewModel<*, *>>) {
        compositeDisposable += Observable.fromCallable {
            val newAdapterItemList = viewModels.map { it }
            val result = DiffUtil.calculateDiff(DiffUtilCallback(adapterItems, newAdapterItemList))
            this.adapterItems.clear()
            this.adapterItems.addAll(newAdapterItemList)
            return@fromCallable result
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.dispatchUpdatesTo(this)
            }
    }

    override fun onViewDetachedFromWindow(holder: FeedViewHolder) {
        compositeDisposable.clear()
        super.onViewDetachedFromWindow(holder)
    }
}
