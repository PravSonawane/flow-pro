package ui.lib.databinding.adapters

import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.ViewHolder

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
