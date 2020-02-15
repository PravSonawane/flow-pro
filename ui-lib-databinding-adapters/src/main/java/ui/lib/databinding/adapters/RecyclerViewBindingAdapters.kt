package ui.lib.databinding.adapters

import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ui.lib.views.ItemViewModel
import ui.lib.views.RecyclerViewAdapter

fun verticalLinear(context: Context): RecyclerView.LayoutManager {
    return LinearLayoutManager(
        context,
        LinearLayoutManager.VERTICAL,
        false
    )
}

fun verticalGrid(context: Context, columnCount: Int): RecyclerView.LayoutManager {
    return GridLayoutManager(
        context,
        columnCount,
        GridLayoutManager.VERTICAL,
        false
    )
}

fun dividerItemDecorationVertical(context: Context): ItemDecoration {
    return DividerItemDecoration(
        context,
        DividerItemDecoration.VERTICAL
    )
}

/**
 * [BindingAdapter] that sets data for a [RecyclerView] that renders [ItemViewModel]s.
 *
 * If an [RecyclerView.Adapter] has not been set for this [RecyclerView], a new
 * [RecyclerViewAdapter] will be created and set using [RecyclerViewAdapter.updateData]. If data is null,
 * an empty list will be set.
 *
 * @param recyclerView The [RecyclerView] that will render the data
 * @param data a list of [ItemViewModel]
 */
@BindingAdapter("adapterItems")
fun setAdapterItems(
    recyclerView: RecyclerView,
    data: List<ItemViewModel<Any, Any>>?
) {
    val adapter: RecyclerViewAdapter =
        (recyclerView.adapter as? RecyclerViewAdapter) ?: RecyclerViewAdapter()
    if (recyclerView.adapter == null) recyclerView.adapter = adapter
    adapter.updateData(data?.map { it }?.toList() ?: emptyList())
}
