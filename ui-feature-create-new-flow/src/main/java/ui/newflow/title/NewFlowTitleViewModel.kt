package ui.newflow.title

import androidx.lifecycle.MutableLiveData
import core.lib.result.Result
import domain.flow.models.Flow
import domain.flow.models.SaveFlowInput
import domain.flow.usecases.SaveOrUpdateFlowUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.lib.base.BaseViewModel
import javax.inject.Inject
import core.lib.rxutils.plusAssign

internal const val DEFAULT_FLOW_NAME_PREFIX = "My Flow "

class NewFlowTitleViewModel @Inject constructor(
    private val saveOrUpdateFlowUseCase: SaveOrUpdateFlowUseCase
) : BaseViewModel<NewFlowTitleViewModel.Event>() {

    var title: MutableLiveData<String> = MutableLiveData("$DEFAULT_FLOW_NAME_PREFIX 1")
    var error: String = ""

    fun onNext() {
        compositeDisposable += saveOrUpdateFlowUseCase.invoke(
            SaveFlowInput(
                name = title.value
            )
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is Result.OnSuccess -> handleSaveOrUpdateSuccess(it.data)
                }
            }
    }

    private fun handleSaveOrUpdateSuccess(data: Flow) {
        eventStream.onNext(Event.OnNext(data))
    }

    sealed class Event {
        data class OnNext(val flow: Flow) : Event()
    }
}