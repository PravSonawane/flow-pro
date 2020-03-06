package ui.feature.flow.list

import domain.models.flow.Flow
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.toolbar.ToolbarViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val streamFactory: StreamFactory,
    private val liveDataFactory: LiveDataFactory
) {

    fun create(analyticsKey: String, flow: Flow): FlowListItemViewModel {
        return FlowListItemViewModel(
            analyticsKey,
            streamFactory,
            liveDataFactory,
            flow
        )
    }

    fun toolbar(analyticsKey: String): ToolbarViewModel {
        return ToolbarViewModel(
            analyticsKey,
            liveDataFactory,
            streamFactory,
            R.layout.layout_toolbar,
            BR.viewModel
        )
    }
}
