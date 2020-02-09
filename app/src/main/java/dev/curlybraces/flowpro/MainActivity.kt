package dev.curlybraces.flowpro

import android.os.Bundle
import app.di.AppComponent
import app.di.DaggerMainComponent
import app.di.MainComponent
import app.di.MainModule
import ui.lib.base.BaseActivity

class MainActivity : BaseActivity() {

    private val mainComponent: MainComponent by lazy {
        DaggerMainComponent.builder()
            .appComponent(appComponent())
            .mainModule(MainModule())
            .build()
    }

    override fun appComponent(): AppComponent {
        return (application as FlowProApp).appComponent()
    }

    override fun mainComponent(): MainComponent {
        return mainComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // dagger injection
        mainComponent.injectIn(this)
    }
}
