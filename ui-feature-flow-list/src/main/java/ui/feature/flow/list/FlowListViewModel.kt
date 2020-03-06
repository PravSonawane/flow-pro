package ui.feature.flow.list

import androidx.lifecycle.MutableLiveData
import core.lib.plugin.Plugin
import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import core.lib.usecase.common.BusinessData
import core.lib.usecase.common.BusinessUseCase
import domain.flow.usecases.GetAllFlowsUseCase
import domain.models.flow.Flow
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject
import javax.inject.Named

class FlowListViewModel @Inject constructor(
    @Named(GetAllFlowsUseCase.NAMED) getAllFlowsUseCase: BusinessUseCase<Unit, List<Flow>>,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    private val viewModelFactory: ViewModelFactory
) : BaseViewModel<FlowListViewModel.Input, FlowListViewModel.Event>(
    "e0b523fe-2f8e",
    streamFactory,
    R.layout.fragment_flow_list
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val items: MutableLiveData<List<FlowListItemViewModel>> =
        liveDataFactory.mutableLiveData("2ae5bd06-7b9a", emptyList())

    init {
        items.observeForever {
            compositeDisposable += Observable.merge(it.map { item -> item.observeOutput() })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { event ->
                    when (event) {
                        is FlowListItemViewModel.Event.OnViewFlow -> handleOnViewFlow(event.flow)
                    }
                }
        }

        compositeDisposable += getAllFlowsUseCase(BusinessData("58993dea-6ddf", Plugin("3f39506e-6669"), Unit))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetAllFlowsSuccess(it.data)
                    is Result.OnError -> handleGetAllFlowsError(it.domainError)
                }
            }
    }

    fun onCreateNewFlow() {
        sendOutput(Event.OnCreateNewFlow)
    }

    private fun handleGetAllFlowsSuccess(data: List<Flow>) {
        items.value = data.map { viewModelFactory.create("1c85e2e8-1c87", it) }
    }

    private fun handleGetAllFlowsError(domainError: DomainError) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    private fun handleOnViewFlow(flow: Flow) {
        sendOutput(
            Event.OnViewFlow(
                flow
            )
        )
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
