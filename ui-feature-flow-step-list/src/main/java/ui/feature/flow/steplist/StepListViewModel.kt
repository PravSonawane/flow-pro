package ui.feature.flow.steplist

import core.lib.plugin.Plugin
import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import core.lib.usecase.common.BusinessData
import core.lib.usecase.common.BusinessUseCase
import domain.flow.usecases.GetFlowByIdUseCase
import domain.flow.usecases.GetStepsInput
import domain.flow.usecases.GetStepsUseCase
import domain.models.flow.Flow
import domain.models.flow.Step
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.LayoutViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.list.ListViewModel
import ui.lib.views.list.ListViewModelFactory
import javax.inject.Inject
import javax.inject.Named

class StepListViewModel @Inject constructor(
    val getFlowByIdUseCase: GetFlowByIdUseCase,
    @Named(GetStepsUseCase.NAMED)
    val getStepsUseCase: BusinessUseCase<GetStepsInput, List<Step>>,
    private val viewModelFactory: ViewModelFactory,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory,
    listViewModelFactory: ListViewModelFactory
) : LayoutViewModel<StepListViewModel.Input, StepListViewModel.Event>(
    "ba45622c-d74a",
    streamFactory,
    R.layout.layout_step_list
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val stepListViewModel = listViewModelFactory.create<StepItemViewModel>("b277b859-277a")
    val flow = liveDataFactory.mutableLiveData<Flow>("a4efee98-acaa")

    init {
        compositeDisposable += stepListViewModel.observeOutput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { step ->
                when (step) {
                    is StepItemViewModel.Event.OnSelectStep -> handleOnSelectStep(step.step)
                }
            }

        compositeDisposable += observeInput()
            .flatMap {
                getStepsUseCase(
                    BusinessData(
                        "41508dfb-95b4",
                        Plugin("23145985-698d"),
                        GetStepsInput(it.flowId, it.stepId)
                    )
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleSuccess(it.data)
                    is Result.OnError -> handleError(it.domainError)
                }
            }

        compositeDisposable += observeInput()
            .flatMap {
                    getFlowByIdUseCase(
                        BusinessData(
                            "7880cff2-530e",
                            Plugin("152a67de-3676"),
                            it.flowId
                        )
                    )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetFlowByIdSuccess(it.data)
                }
            }
    }

    private fun handleSuccess(steps: List<Step>) {
        val items: List<StepItemViewModel> = steps.map {
            viewModelFactory.create("73d5b9c7-fbc4", it)
        }
        this.stepListViewModel.sendInput(ListViewModel.Input(items))
    }

    private fun handleError(error: DomainError) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    private fun handleGetFlowByIdSuccess(data: Flow) {
        flow.value = data
    }

    fun onNext() {
        sendOutput(Event.OnNewStep)
    }

    private fun handleOnSelectStep(step: Step) {
        flow.value?.let { sendOutput(Event.OnViewStep(it.id, step)) }
    }

    data class Input(val flowId: String, val stepId: String? = null)

    sealed class Event {
        object OnNewStep : Event()
        data class OnViewStep(val flowId: String, val step: Step) : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}