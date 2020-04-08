package app.di.test

import android.app.Activity
import app.di.annotations.ActivityContext
import dagger.Module
import dagger.Provides
import ui.lib.di.ActivityScope
import ui.navigation.Navigator
import ui.navigation.test.FakeNavigator

@Module
class TestMainModule {

    @Provides
    @ActivityScope
    @ActivityContext
    fun activity(): Activity {
        return FakeActivity()
    }

    @Provides
    @ActivityScope
    fun navigator(): Navigator {
        return FakeNavigator()
    }
}
