package ui.newflow.selectnode

import domain.models.flow.Node
import javax.inject.Inject

class ViewModelFactory @Inject constructor() {

    fun create(node: Node): SelectNodeItemViewModel {
        return SelectNodeItemViewModel(node)
    }
}