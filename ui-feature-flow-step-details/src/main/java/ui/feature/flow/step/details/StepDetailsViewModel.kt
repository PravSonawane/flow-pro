package ui.feature.flow.step.details

import androidx.lifecycle.MutableLiveData
import core.lib.plugin.Plugin
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import core.lib.usecase.common.BusinessData
import core.lib.usecase.common.BusinessUseCase
import domain.flow.usecases.GetFlowByIdUseCase
import domain.models.flow.Flow
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.BaseViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject
import javax.inject.Named

class StepDetailsViewModel @Inject constructor(
    @Named(GetFlowByIdUseCase.NAMED) val getFlowByIdUseCase: BusinessUseCase<String, Flow>,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory
) : BaseViewModel<StepDetailsViewModel.Input, StepDetailsViewModel.Event>(
    "ba45622c-d74a",
    streamFactory
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val flowName: MutableLiveData<String> = liveDataFactory.mutableLiveData("a4efee98-acaa")

    init {

        compositeDisposable += observeInput()
            .flatMap {
                when (it) {
                    is Input.FlowId -> getFlowByIdUseCase(
                        BusinessData(
                            "7880cff2-530e",
                            Plugin("152a67de-3676"),
                            it.id
                        )
                    )
                }
            }
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetFlowByIdSuccess(it.data)
                }
            }
    }

    private fun handleGetFlowByIdSuccess(data: Flow) {
        flowName.value = data.name
    }

    fun onNext() {
        sendOutput(Event.OnNewStep)
    }

    sealed class Input {
        data class FlowId(val id: String) : Input()
    }

    sealed class Event {
        object OnNewStep : Event()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
