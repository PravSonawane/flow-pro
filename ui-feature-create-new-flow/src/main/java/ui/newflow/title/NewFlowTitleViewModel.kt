package ui.newflow.title

import ui.lib.base.BaseViewModel
import javax.inject.Inject

internal const val DEFAULT_FLOW_NAME_PREFIX = "My Flow "

class NewFlowTitleViewModel @Inject constructor() : BaseViewModel<NewFlowTitleViewModel.Event>() {

    var title: String = "$DEFAULT_FLOW_NAME_PREFIX 1"
    var error: String = ""

    fun onNext() {
        eventStream.onNext(Event.OnNext)
    }

    sealed class Event {
        object OnNext : Event()
    }
}