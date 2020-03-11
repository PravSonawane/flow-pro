package ui.lib.base

import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import core.lib.rxutils.ReusableCompositeDisposable
import ui.lib.di.BaseMainComponent

abstract class BaseFragment : Fragment() {
    protected val compositeDisposable = ReusableCompositeDisposable()

    abstract fun mainComponent(): BaseMainComponent

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }
}
