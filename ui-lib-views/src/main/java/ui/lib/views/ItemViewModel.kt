package ui.lib.views

import androidx.annotation.IntegerRes
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import ui.lib.base.BaseViewModel

abstract class ItemViewModel<T>(
    @LayoutRes val layoutId: Int,
    @IntegerRes val variableId: Int,
    val viewType: Int
) : BaseViewModel<T>()