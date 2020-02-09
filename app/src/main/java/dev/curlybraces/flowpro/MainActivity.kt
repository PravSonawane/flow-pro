package dev.curlybraces.flowpro

import android.os.Bundle
import ui.lib.base.BaseActivity

class MainActivity : BaseActivity() {

    private val mainComponent: MainComponent by lazy {
        DaggerMainComponent.builder()
            .appComponent(appComponent())
            .mainModule(MainModule())
            .build()
    }

    override fun appComponent(): AppComponent {
        return (application as FlowProApp).baseAppComponent() as AppComponent
    }

    override fun mainComponent(): MainComponent {
        return mainComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
