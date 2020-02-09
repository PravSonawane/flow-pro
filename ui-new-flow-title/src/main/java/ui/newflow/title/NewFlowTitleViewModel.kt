package ui.newflow.title

import ui.lib.base.BaseViewModel
import javax.inject.Inject

class NewFlowTitleViewModel @Inject constructor() : BaseViewModel<NewFlowTitleViewModel.Event>() {

    var title: String = ""
    var error: String = ""

    sealed class Event
}