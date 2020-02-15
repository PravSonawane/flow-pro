package ui.lib.base

import androidx.appcompat.app.AppCompatActivity
import ui.lib.di.BaseAppComponent
import ui.lib.di.BaseMainComponent

abstract class BaseActivity : AppCompatActivity() {
    abstract fun appComponent(): BaseAppComponent
    abstract fun mainComponent(): BaseMainComponent
}
