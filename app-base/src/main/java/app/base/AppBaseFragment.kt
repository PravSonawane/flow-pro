package app.base

import app.di.MainComponent
import ui.lib.base.BaseActivity
import ui.lib.base.BaseFragment

abstract class AppBaseFragment : BaseFragment() {

    override fun mainComponent(): MainComponent {
        return (activity as BaseActivity).mainComponent() as MainComponent
    }
}