package ui.lib.views

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory

/** A [RecyclerView.Adapter] view type for [ItemListHeaderViewModel] */
const val VIEW_TYPE_LIST_HEADER = 11001

/** A [RecyclerView.Adapter] view type for [ItemListButtonViewModel] */
const val VIEW_TYPE_LIST_BUTTON = 11002

class ItemListHeaderViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory
) : ItemViewModel<ItemListHeaderViewModel.Input, ItemListHeaderViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.item_list_header,
    BR.viewModel,
    VIEW_TYPE_LIST_HEADER
) {

    val headerText: LiveData<CharSequence> = liveDataFactory.liveData("18a333b8-d6a9")

    sealed class Input
    sealed class Event
}

class ItemListButtonViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory
) : ItemViewModel<ItemListButtonViewModel.Input, ItemListButtonViewModel.Output>(
    analyticsKey,
    streamFactory,
    R.layout.item_list_button,
    BR.viewModel,
    VIEW_TYPE_LIST_BUTTON
) {

    val buttonText: LiveData<CharSequence> = liveDataFactory.liveData("17df35b8-486c")

    fun onClick() {
        sendOutput(Output.OnClick)
    }

    sealed class Input

    sealed class Output {
        object OnClick : Output()
    }
}
