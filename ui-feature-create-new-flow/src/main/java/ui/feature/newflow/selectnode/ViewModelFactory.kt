package ui.feature.newflow.selectnode

import domain.models.flow.Node
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val streamFactory: StreamFactory,
    private val liveDataFactory: LiveDataFactory
) {

    fun create(analyticsKey: String, node: Node): SelectNodeItemViewModel {
        return SelectNodeItemViewModel(analyticsKey, streamFactory, liveDataFactory, node)
    }
}
