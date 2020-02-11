package ui.lib.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

/** A [RecyclerView.Adapter] view type for [ItemListHeaderViewModel] */
const val VIEW_TYPE_LIST_HEADER = 11001

/** A [RecyclerView.Adapter] view type for [ItemListButtonViewModel] */
const val VIEW_TYPE_LIST_BUTTON = 11002

class ItemListHeaderViewModel : ItemViewModel<ItemListHeaderViewModel.Event>(
    R.layout.item_list_header,
    BR.viewModel,
    VIEW_TYPE_LIST_HEADER
) {
    val headerText: LiveData<CharSequence> = MutableLiveData()
    sealed class Event
}

class ItemListButtonViewModel : ItemViewModel<ItemListButtonViewModel.Event>(
    R.layout.item_list_button,
    BR.viewModel,
    VIEW_TYPE_LIST_BUTTON
) {
    val buttonText: LiveData<CharSequence> = MutableLiveData()

    fun onClick() {
        eventStream.onNext(Event.OnClick)
    }

    sealed class Event {
        object OnClick : Event()
    }
}