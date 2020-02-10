package ui.lib.databinding.adapters

import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ui.lib.base.BaseViewModel
import ui.lib.views.RecyclerViewAdapter

/** Android databindings for [RecyclerView]*/
class RecyclerViewBindingAdapters {

    companion object {

        @JvmStatic
        @BindingAdapter("adapter")
        fun onBindAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<ViewHolder>) {
            recyclerView.adapter = adapter
        }

        @JvmStatic
        @BindingAdapter("itemDecoration")
        fun onItemDecoration(recyclerView: RecyclerView, itemDecoration: ItemDecoration) {
            recyclerView.addItemDecoration(itemDecoration)
        }

        @JvmStatic
        fun verticalLinear(context: Context): RecyclerView.LayoutManager {
            return LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        @JvmStatic
        fun verticalGrid(context: Context, columnCount: Int): RecyclerView.LayoutManager {
            return GridLayoutManager(
                context,
                columnCount,
                GridLayoutManager.VERTICAL,
                false
            )
        }

        @JvmStatic
        fun dividerItemDecorationVertical(context: Context): ItemDecoration {
            return DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        }
    }
}

/**
 * [BindingAdapter] that sets data for a [RecyclerView] that renders [FeedAdapterItem]s.
 *
 * If an [RecyclerView.Adapter] has not been set for this [RecyclerView], a new
 * [RecyclerViewAdapter] will be created and set using [RecyclerViewAdapter.updateData]. If data is null,
 * an empty list will be set.
 *
 * @param recyclerView The [RecyclerView] that will render the data
 * @param data a list of [BaseViewModel]
 */
@BindingAdapter("adapterClass", "adapterItems", requireAll = true)
fun setAdapterItems(
    recyclerView: RecyclerView,
    adapterClass: Class<out RecyclerViewAdapter>,
    data: List<BaseViewModel<Any>>?
) {
    val adapter: RecyclerViewAdapter =
        (recyclerView.adapter as? RecyclerViewAdapter) ?: adapterClass.newInstance()
    if (recyclerView.adapter == null) recyclerView.adapter = adapter
    adapter.updateData(data?.map { it }?.toList() ?: emptyList())
}
