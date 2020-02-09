package ui.lib.base

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import ui.lib.di.BaseMainComponent

abstract class BaseFragment: Fragment() {
    protected val compositeDisposable = CompositeDisposable()

    abstract fun mainComponent(): BaseMainComponent

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }
}