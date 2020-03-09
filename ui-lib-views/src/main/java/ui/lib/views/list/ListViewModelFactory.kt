package ui.lib.views.list

import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.ItemViewModel
import javax.inject.Inject

class ListViewModelFactory @Inject constructor(
    private val streamFactory: StreamFactory,
    private val liveDataFactory: LiveDataFactory
) {

    fun <T : ItemViewModel<ListViewModel.ItemInput, ListViewModel.ItemOutput>> create(analyticsKey: String): ListViewModel<T> {
        return ListViewModel(analyticsKey, liveDataFactory, streamFactory)
    }
}