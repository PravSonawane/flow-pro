package ui.lib.views.toolbar

import androidx.annotation.IntegerRes
import androidx.lifecycle.MutableLiveData
import core.lib.rxutils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ui.lib.base.LayoutViewModel
import ui.lib.utils.LiveDataFactory
import ui.lib.utils.StreamFactory
import javax.inject.Inject

class ToolbarViewModel @Inject constructor(
    analyticsKey: String,
    liveDataFactory: LiveDataFactory,
    streamFactory: StreamFactory,
    layoutId: Int,
    @IntegerRes val variableId: Int
) : LayoutViewModel<ToolbarViewModel.Input, Unit>(analyticsKey, streamFactory, layoutId) {

    val title: MutableLiveData<CharSequence> = liveDataFactory.mutableLiveData(analyticsKey)

    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable += observeInput()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                title.value = it.title
            }
    }

    data class Input(
        val title: CharSequence
    )

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
