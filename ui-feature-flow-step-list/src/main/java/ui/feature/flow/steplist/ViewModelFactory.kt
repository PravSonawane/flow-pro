package ui.feature.flow.steplist

import domain.models.flow.Node
import domain.models.flow.Step
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val streamFactory: StreamFactory,
    private val liveDataFactory: LiveDataFactory
) {

    fun create(analyticsKey: String, step: Step): FlowStepItemViewModel {
        return FlowStepItemViewModel(analyticsKey, streamFactory, liveDataFactory, step)
    }
}
