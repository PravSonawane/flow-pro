package ui.newflow.title

import ui.lib.base.BaseViewModel
import javax.inject.Inject

class NewFlowTitleViewModel @Inject constructor() : BaseViewModel<NewFlowTitleViewModel.Event>() {

    var title: String = "My Flow 1"
    var error: String = ""

    fun onNext() {
        eventStream.onNext(Event.OnNext)
    }

    sealed class Event {
        object OnNext : Event()
    }
}