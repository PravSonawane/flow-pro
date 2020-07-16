package ui.lib.views.text

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import core.lib.rxutils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.ItemViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.viewmodels.BR
import ui.lib.viewmodels.R
import ui.lib.views.types.TextResourceBinding
import javax.inject.Inject

class TextInputLayoutViewModel @Inject constructor(
    analyticsKey: String,
    streamFactory: StreamFactory,
    liveDataFactory: LiveDataFactory
) : ItemViewModel<TextInputLayoutViewModel.Input, TextInputLayoutViewModel.Event>(
    analyticsKey,
    streamFactory,
    R.layout.layout_text_input,
    BR.viewModel
) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val text: MutableLiveData<TextResourceBinding> = liveDataFactory.mutableLiveData(analyticsKey)
    val hint: MutableLiveData<TextResourceBinding> = liveDataFactory.mutableLiveData(analyticsKey)
    val errorEnabled: MutableLiveData<Boolean> = liveDataFactory.mutableLiveData(analyticsKey)

    // TODO not bound to the UI yet
    val error: MutableLiveData<CharSequence> = liveDataFactory.mutableLiveData(analyticsKey)

    init {
        compositeDisposable += observeInput()
            .ofType(Input.SetData::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                text.postValue(TextResourceBinding(it.data.text, it.data.textRes))
                hint.postValue(TextResourceBinding(it.data.hint, it.data.hintRes))
                error.value = it.data.error
                errorEnabled.value = it.data.errorEnabled

                // send data as output state
                sendOutput(Event.State(it.data))
            }
    }

    sealed class Input {
        data class SetData(val data: Data): Input()
    }

    sealed class Event {
        data class State(val data: Data): Event()
    }

    data class Data(
        val text: CharSequence? = null,
        val hint: CharSequence? = null,
        @StringRes val textRes: Int? = null,
        @StringRes val hintRes: Int? = null,
        val error: CharSequence? = null,
        val errorEnabled: Boolean = false
    )

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
