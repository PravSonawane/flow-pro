package ui.lib.views.toolbar

import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.R
import ui.lib.views.BR
import javax.inject.Inject

class ToolbarViewModelFactory @Inject constructor(
    private val streamFactory: StreamFactory,
    private val liveDataFactory: LiveDataFactory
) {
    fun create(analyticsKey: String): ToolbarViewModel {
        return ToolbarViewModel(
            analyticsKey,
            liveDataFactory,
            streamFactory,
            R.layout.layout_toolbar,
            BR.viewModel
        )
    }
}
