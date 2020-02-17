package dev.curlybraces.flowpro

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.di.AppComponent
import app.di.DaggerMainComponent
import app.di.MainComponent
import app.di.MainModule
import com.google.firebase.analytics.FirebaseAnalytics
import dev.curlybraces.flowpro.databinding.ActivityMainBinding
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
        val instance = FirebaseAnalytics.getInstance(application.applicationContext)
        instance.logEvent(FirebaseAnalytics.Event.APP_OPEN, Bundle())
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // dagger injection
        mainComponent.injectIn(this)
    }
}
