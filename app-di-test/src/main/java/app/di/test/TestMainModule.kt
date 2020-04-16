package app.di.test

import dagger.Module
import dagger.Provides
import ui.lib.di.ActivityScope
import ui.navigation.Navigator
import ui.navigation.test.FakeNavigator

@Module
class TestMainModule {

    @Provides
    @ActivityScope
    fun navigator(): Navigator {
        return FakeNavigator()
    }
}
