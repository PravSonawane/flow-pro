package ui.lib.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import ui.lib.utils.StreamFactory

/** A [RecyclerView.Adapter] view type for [ItemListHeaderViewModel] */
const val VIEW_TYPE_LIST_HEADER = 11001

/** A [RecyclerView.Adapter] view type for [ItemListButtonViewModel] */
const val VIEW_TYPE_LIST_BUTTON = 11002

class ItemListHeaderViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    val headerText: LiveData<CharSequence> = MutableLiveData()
) : ItemViewModel<ItemListHeaderViewModel.Input, ItemListHeaderViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.item_list_header,
    BR.viewModel,
    VIEW_TYPE_LIST_HEADER
) {
    sealed class Input
    sealed class Event

    override fun dispose() {}
}

class ItemListButtonViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    val buttonText: LiveData<CharSequence> = MutableLiveData()
) : ItemViewModel<ItemListButtonViewModel.Input, ItemListButtonViewModel.Output>(
    analyticsKey,
    streamFactory,
    R.layout.item_list_button,
    BR.viewModel,
    VIEW_TYPE_LIST_BUTTON
) {
    fun onClick() {
        sendOutput(Output.OnClick)
    }

    sealed class Input

    sealed class Output {
        object OnClick : Output()
    }

    override fun dispose() {}
}