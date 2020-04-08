package app.di

import android.app.Activity
import androidx.annotation.IntegerRes
import app.di.annotations.ActivityContext
import dagger.Module
import dagger.Provides
import ui.lib.di.ActivityScope
import ui.navigation.Navigator
import ui.navigation.SimpleNavigator

@Module
class MainModule(
    private val activity: Activity,
    @IntegerRes private val navHostViewId: Int
) {

    @Provides
    @ActivityScope
    @ActivityContext
    fun activity(): Activity {
        return activity
    }

    @Provides
    @ActivityScope
    fun navigator(@ActivityContext activity: Activity): Navigator {
        return SimpleNavigator(activity, navHostViewId)
    }
}
