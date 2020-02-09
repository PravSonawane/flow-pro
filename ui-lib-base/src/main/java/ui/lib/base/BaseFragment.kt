package ui.lib.base

import androidx.fragment.app.Fragment
import ui.lib.di.BaseMainComponent

abstract class BaseFragment: Fragment() {
    abstract fun mainComponent(): BaseMainComponent
}