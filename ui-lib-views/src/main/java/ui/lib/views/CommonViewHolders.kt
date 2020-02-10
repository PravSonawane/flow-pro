package ui.lib.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import ui.lib.base.BaseViewModel
import ui.lib.views.databinding.ItemListButtonBinding
import ui.lib.views.databinding.ItemListHeaderBinding

/** A [RecyclerView.Adapter] view type for [ItemListHeaderViewModel] */
const val VIEW_TYPE_LIST_HEADER = 11001

/** A [RecyclerView.Adapter] view type for [ItemListButtonViewModel] */
const val VIEW_TYPE_LIST_BUTTON = 11002

class ItemListHeaderViewModel : BaseViewModel<ItemListHeaderViewModel.Event>() {
    val headerText: LiveData<CharSequence> = MutableLiveData()

    sealed class Event
}

class ItemListButtonViewModel : BaseViewModel<ItemListButtonViewModel.Event>() {
    val buttonText: LiveData<CharSequence> = MutableLiveData()

    fun onClick() {
        eventStream.onNext(Event.OnClick)
    }

    sealed class Event {
        object OnClick : Event()
    }
}

class ItemListHeaderViewHolder(parent: ViewGroup) : FeedViewHolder(inflate(parent)) {

    override fun bind(feedAdapterItem: FeedAdapterItem) {
        (binding as ItemListHeaderBinding).viewModel =
            feedAdapterItem.data as? ItemListHeaderViewModel
    }

    companion object {
        /** Inflates the view associated with this [RecyclerView.ViewHolder] */
        private fun inflate(parent: ViewGroup?): ViewDataBinding {
            return DataBindingUtil.inflate<ItemListHeaderBinding>(
                LayoutInflater.from(parent?.context),
                R.layout.item_list_header,
                parent,
                false
            )
        }
    }
}

class ItemListButtonViewHolder(parent: ViewGroup) : FeedViewHolder(inflate(parent)) {

    override fun bind(feedAdapterItem: FeedAdapterItem) {
        (binding as ItemListButtonBinding).viewModel =
            feedAdapterItem.data as? ItemListButtonViewModel
    }

    companion object {
        /** Inflates the view associated with this [RecyclerView.ViewHolder] */
        private fun inflate(parent: ViewGroup?): ViewDataBinding {
            return DataBindingUtil.inflate<ItemListButtonBinding>(
                LayoutInflater.from(parent?.context),
                R.layout.item_list_button,
                parent,
                false
            )
        }
    }
}