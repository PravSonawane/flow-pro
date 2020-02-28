package ui.feature.flow.selectstep

import domain.models.flow.Node
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val streamFactory: StreamFactory,
    private val liveDataFactory: LiveDataFactory
) {

    fun create(analyticsKey: String, node: Node): SelectStepItemViewModel {
        return SelectStepItemViewModel(
            analyticsKey,
            streamFactory,
            liveDataFactory,
            node
        )
    }
}
