package ui.newflow.selectnode

import domain.models.flow.Node
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val streamFactory: StreamFactory
) {

    fun create(analyticsKey: String, node: Node): SelectNodeItemViewModel {
        return SelectNodeItemViewModel(analyticsKey, streamFactory, node)
    }
}
