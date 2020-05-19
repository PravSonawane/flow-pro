package ui.lib.views.text

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import core.lib.rxutils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.ItemViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import ui.lib.views.BR
import ui.lib.views.R
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

    val text: MutableLiveData<CharSequence> = liveDataFactory.mutableLiveData(analyticsKey)
    val hintRes: MutableLiveData<Int> = liveDataFactory.mutableLiveData(analyticsKey)
    @JvmField val isHintRes: MutableLiveData<Boolean> = liveDataFactory.mutableLiveData(analyticsKey)
    val errorEnabled: MutableLiveData<Boolean> = liveDataFactory.mutableLiveData(analyticsKey)

    // TODO not bound to the UI yet
    val error: MutableLiveData<CharSequence> = liveDataFactory.mutableLiveData(analyticsKey)

    init {
        compositeDisposable += observeInput()
            .ofType(Input.SetData::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                text.value = it.data.text
                error.value = it.data.error
                isHintRes.value = it.data.hintRes != null
                if(it.data.hintRes != null && it.data.hintRes > 0) hintRes.value = it.data.hintRes
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
        val error: CharSequence? = null,
        @StringRes val hintRes: Int? = null,
        val errorEnabled: Boolean = false
    )

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
