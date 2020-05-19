package ui.lib.views.text

import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class TextInputLayoutViewModelFactory @Inject constructor(
    private val streamFactory: StreamFactory,
    private val liveDataFactory: LiveDataFactory
) {

    fun create(analyticsKey: String): TextInputLayoutViewModel {
        return TextInputLayoutViewModel(analyticsKey, streamFactory, liveDataFactory)
    }
}