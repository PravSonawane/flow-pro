package ui.feature.flow.steplist

import androidx.lifecycle.MutableLiveData
import core.lib.plugin.Plugin
import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import core.lib.usecase.common.BusinessData
import core.lib.usecase.common.BusinessUseCase
import domain.flow.usecases.GetAllStepsInput
import domain.flow.usecases.GetAllStepsUseCase
import domain.flow.usecases.GetFlowByIdUseCase
import domain.models.flow.Flow
import domain.models.flow.Step
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.LayoutViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.toolbar.ToolbarViewModel
import ui.lib.views.toolbar.ToolbarViewModelFactory
import javax.inject.Inject
import javax.inject.Named

class AllStepListViewModel @Inject constructor(
    @Named(GetFlowByIdUseCase.NAMED) val getFlowByIdUseCase: BusinessUseCase<String, Flow>,
    @Named(GetAllStepsUseCase.NAMED) val getAllStepsUseCase: BusinessUseCase<GetAllStepsInput, List<Step>>,
    toolbarViewModelFactory: ToolbarViewModelFactory,
    private val viewModelFactory: ViewModelFactory,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory
) : LayoutViewModel<AllStepListViewModel.Input, AllStepListViewModel.Event>(
    "877c5474-65e0",
    streamFactory,
    R.layout.layout_step_list_type_all
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val items: MutableLiveData<List<StepItemViewModel>> =
        liveDataFactory.mutableLiveData("1de6d864-fd8a", emptyList())
    val flow: MutableLiveData<Flow> = liveDataFactory.mutableLiveData("c6795166-d62c")

    val toolbarViewModel: ToolbarViewModel = toolbarViewModelFactory.create("e583fbab-f77a")

    init {
        toolbarViewModel.sendInput(ToolbarViewModel.Input("Flow Steps"))

        items.observeForever {
            compositeDisposable += Observable.merge(it.map { steps -> steps.observeOutput() })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { step ->
                    when (step) {
                        is StepItemViewModel.Event.OnSelectStep -> handleOnViewStep(step.step)
                    }
                }
        }

        compositeDisposable += observeInput()
            .flatMap {
                getAllStepsUseCase(
                    BusinessData(
                        "bac5054a-ce14",
                        Plugin("c208efa2-554a"),
                        GetAllStepsInput(it.flowId)
                    )
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetAllStepsSuccess(it.data)
                    is Result.OnError -> handleGetAllStepsError(it.domainError)
                }
            }

        compositeDisposable += observeInput()
            .flatMap {
                    getFlowByIdUseCase(
                        BusinessData(
                            "b15477ff-5711",
                            Plugin("bdae4ab5-6f8d"),
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

    private fun handleGetAllStepsSuccess(steps: List<Step>) {
        val items: List<StepItemViewModel> = steps.map {
            viewModelFactory.create("6bed4afd-a3dd", it)
        }
        this.items.value = items
    }

    private fun handleGetAllStepsError(error: DomainError) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    private fun handleGetFlowByIdSuccess(data: Flow) {
        flow.value = data
    }

    private fun handleOnViewStep(step: Step) {
        flow.value?.let { sendOutput(Event.OnViewStep(it.id, step)) }
    }

    data class Input(val flowId: String)

    sealed class Event {
        data class OnViewStep(val flowId: String, val step: Step) : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
