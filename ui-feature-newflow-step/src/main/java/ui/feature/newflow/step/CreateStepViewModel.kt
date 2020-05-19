package ui.feature.newflow.step

import androidx.lifecycle.MutableLiveData
import core.lib.result.Result
import core.lib.rxutils.plusAssign
import domain.flow.usecases.get.flow.GetFlowByIdUseCase
import domain.models.flow.Flow
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.LayoutViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.text.TextInputLayoutViewModel
import ui.lib.views.text.TextInputLayoutViewModelFactory
import javax.inject.Inject

class CreateStepViewModel @Inject constructor(
    val getFlowByIdUseCase: GetFlowByIdUseCase,
    textInputLayoutViewModelFactory: TextInputLayoutViewModelFactory,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory
) : LayoutViewModel<CreateStepViewModel.Input, CreateStepViewModel.Event>(
    "e2fc2772-418e",
    streamFactory,
    R.layout.fragment_create_step
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val flow: MutableLiveData<Flow> = liveDataFactory.mutableLiveData("6fad0157-ba41")
    val stepNameViewModel = textInputLayoutViewModelFactory.create("8c5c88bd-0cb6")
    val stepDescriptionViewModel = textInputLayoutViewModelFactory.create("07941ea2-439c")

    init {

        stepNameViewModel.sendInput(
            TextInputLayoutViewModel.Input.SetData(
                TextInputLayoutViewModel.Data(
                    hintRes = R.string.label_step_name
                )
            )
        )

        stepDescriptionViewModel.sendInput(
            TextInputLayoutViewModel.Input.SetData(
                TextInputLayoutViewModel.Data(
                    hintRes = R.string.label_step_description
                )
            )
        )
        compositeDisposable += observeInput()
            .flatMap {
                when (it) {
                    is Input.FlowId -> getFlowByIdUseCase(it.id)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleGetFlowByIdSuccess(it.data)
                }
            }
    }

    private fun handleGetFlowByIdSuccess(data: Flow) {
        flow.value = data
    }

    fun onShowSelectNode() {
        val flow = flow.value
        if (flow != null) {
            sendOutput(Event.OnShowSelectNode(flow))
        } else {
            sendOutput(Event.OnNoFlowError)
        }
    }

    sealed class Input {
        data class FlowId(val id: String) : Input()
    }

    sealed class Event {
        data class OnShowSelectNode(val flow: Flow) : Event()
        object OnNoFlowError : Event()
    }

    override fun onCleared() {
        stepNameViewModel.onCleared()
        stepDescriptionViewModel.onCleared()
        compositeDisposable.dispose()
    }
}
