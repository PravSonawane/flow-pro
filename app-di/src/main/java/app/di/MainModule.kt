package app.di

import android.app.Activity
import app.di.annotations.ActivityContext
import dagger.Module
import dagger.Provides
import ui.lib.di.ActivityScope

@Module
class MainModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    @ActivityContext
    fun activity(): Activity {
        return activity
    }
}
