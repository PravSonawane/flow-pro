package ui.lib.di

import android.app.Application
import android.content.Context
import app.di.annotations.ApplicationContext

interface BaseAppComponent {
    fun application(): Application
    @ApplicationContext fun applicationContext(): Context
}
