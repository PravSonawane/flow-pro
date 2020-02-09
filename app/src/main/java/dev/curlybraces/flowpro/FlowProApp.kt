package dev.curlybraces.flowpro

import ui.lib.base.BaseApplication
import ui.lib.di.BaseAppComponent

class FlowProApp : BaseApplication() {

    private val appComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun baseAppComponent(): BaseAppComponent {
        return appComponent
    }
}