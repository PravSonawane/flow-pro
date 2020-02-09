package ui.lib.base

import android.app.Application
import ui.lib.di.BaseAppComponent

abstract class BaseApplication : Application() {
    abstract fun baseAppComponent(): BaseAppComponent
}