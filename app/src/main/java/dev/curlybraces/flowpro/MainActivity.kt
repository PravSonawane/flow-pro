package dev.curlybraces.flowpro

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
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
            .mainModule(MainModule(this, R.id.nav_host_fragment))
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

        // navigation
        setupNavigation()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
    }
}
