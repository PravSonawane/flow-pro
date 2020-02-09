package dev.curlybraces.flowpro

import app.di.AppComponent
import app.di.DaggerAppComponent
import ui.lib.base.BaseApplication

class FlowProApp : BaseApplication() {

    private val appComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun appComponent(): AppComponent {
        return appComponent
    }
}