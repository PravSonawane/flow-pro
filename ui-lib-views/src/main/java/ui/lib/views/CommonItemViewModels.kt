package ui.lib.views

import androidx.lifecycle.LiveData
import ui.lib.base.ItemViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory

class ItemListHeaderViewModel(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory
) : ItemViewModel<ItemListHeaderViewModel.Input, ItemListHeaderViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.item_list_header,
    BR.viewModel
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
    BR.viewModel
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
