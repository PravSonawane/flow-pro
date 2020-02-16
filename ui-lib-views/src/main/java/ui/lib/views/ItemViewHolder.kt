package ui.lib.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IntegerRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(
    parent: ViewGroup,
    @LayoutRes layoutId: Int,
    @IntegerRes private val variableId: Int
) : FeedViewHolder(inflate(parent, layoutId)) {
    override fun bind(itemViewModel: ItemViewModel<*,*>) {
        binding.setVariable(variableId, itemViewModel)
    }

    companion object {
        /** Inflates the view associated with this [RecyclerView.ViewHolder] */
        private fun inflate(parent: ViewGroup?, layoutId: Int): ViewDataBinding {
            return DataBindingUtil.inflate(LayoutInflater.from(parent?.context), layoutId, parent, false)
        }
    }
}
