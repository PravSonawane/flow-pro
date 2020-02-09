package ui.newflow.selectnode

import ui.lib.base.BaseViewModel
import javax.inject.Inject

internal const val DEFAULT_FLOW_NAME_PREFIX = "My Flow "

class NewFlowSelectNodeViewModel @Inject constructor() : BaseViewModel<NewFlowSelectNodeViewModel.Event>() {

    var selectNode: String = "$DEFAULT_FLOW_NAME_PREFIX 1"
    var error: String = ""

    fun onNext() {
        eventStream.onNext(Event.OnNext)
    }

    sealed class Event {
        object OnNext : Event()
    }
}