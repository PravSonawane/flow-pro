package ui.feature.flow.list

import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import domain.flow.usecases.get.flow.GetAllFlowsUseCase
import domain.models.flow.Flow
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.LayoutViewModel
import ui.lib.utils.StreamFactory
import ui.lib.views.list.ListViewModel
import ui.lib.views.list.ListViewModelFactory
import javax.inject.Inject

class FlowListViewModel @Inject constructor(
    getAllFlowsUseCase: GetAllFlowsUseCase,
    streamFactory: StreamFactory,
    listViewModelFactory: ListViewModelFactory,
    private val viewModelFactory: ViewModelFactory
) : LayoutViewModel<FlowListViewModel.Input, FlowListViewModel.Event>(
    "e0b523fe-2f8e",
    streamFactory,
    R.layout.fragment_flow_list
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val listViewModel: ListViewModel<FlowListItemViewModel> =
        listViewModelFactory.create("2ae5bd06-7b9a")

    init {

        compositeDisposable += listViewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is ListViewModel.Output.OnItemOutput -> handleFlowListViewModelOutput(it.itemOutput)
                }
            }

        compositeDisposable += getAllFlowsUseCase(Unit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetAllFlowsSuccess(it.data)
                    is Result.OnError -> handleGetAllFlowsError(it.domainError)
                }
            }
    }

    private fun handleFlowListViewModelOutput(itemOutput: ListViewModel.ItemOutput) {
        when (itemOutput) {
            is FlowListItemViewModel.Event.OnViewFlow -> handleOnViewFlow(itemOutput.flow)
        }
    }

    fun onCreateNewFlow() {
        sendOutput(Event.OnCreateNewFlow)
    }

    private fun handleGetAllFlowsSuccess(data: List<Flow>) {
        val viewModels = data.map { viewModelFactory.create("1c85e2e8-1c87", it) }
        listViewModel.sendInput(ListViewModel.Input.OnData(viewModels))
    }

    private fun handleGetAllFlowsError(domainError: DomainError) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    private fun handleOnViewFlow(flow: Flow) {
        sendOutput(Event.OnViewFlow(flow))
    }

    sealed class Input

    sealed class Event {
        data class OnViewFlow(val flow: Flow) : Event()
        object OnCreateNewFlow : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
