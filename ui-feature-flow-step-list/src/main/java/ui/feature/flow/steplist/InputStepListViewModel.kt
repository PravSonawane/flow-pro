package ui.feature.flow.steplist

import androidx.lifecycle.MutableLiveData
import core.lib.plugin.Plugin
import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import core.lib.usecase.common.BusinessData
import core.lib.usecase.common.BusinessUseCase
import domain.flow.usecases.GetFlowByIdUseCase
import domain.flow.usecases.GetPossibleInputStepsInput
import domain.flow.usecases.GetPossibleOutputStepsUseCase
import domain.models.flow.Flow
import domain.models.flow.Step
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject
import javax.inject.Named

class InputStepListViewModel @Inject constructor(
    @Named(GetFlowByIdUseCase.NAMED) val getFlowByIdUseCase: BusinessUseCase<String, Flow>,
    @Named(GetPossibleOutputStepsUseCase.NAMED) val getPossibleOutputStepsUseCase: BusinessUseCase<GetPossibleInputStepsInput, List<Step>>,
    private val viewModelFactory: ViewModelFactory,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory
) : BaseViewModel<InputStepListViewModel.Input, InputStepListViewModel.Event>(
    "ba45622c-d74a",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val items: MutableLiveData<List<StepItemViewModel>> =
        liveDataFactory.mutableLiveData("b277b859-277a", emptyList())
    val flow: MutableLiveData<Flow> = liveDataFactory.mutableLiveData("a4efee98-acaa")

    init {

        items.observeForever {
            compositeDisposable += Observable.merge(it.map { steps -> steps.observeOutput() })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { step ->
                    when (step) {
                        is StepItemViewModel.Event.OnSelectStep -> handleOnSelectStep(step.step)
                    }
                }
        }

        compositeDisposable += observeInput()
            .flatMap {
                getPossibleOutputStepsUseCase(
                    BusinessData(
                        "41508dfb-95b4",
                        Plugin("23145985-698d"),
                        GetPossibleInputStepsInput(it.stepId)
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
        this.items.value = items
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

    data class Input(val flowId: String, val stepId: String)

    sealed class Event {
        object OnNewStep : Event()
        data class OnViewStep(val flowId: String, val step: Step) : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
