package app.di.test

import dagger.Module
import dagger.Provides
import ui.lib.di.MainScope
import ui.navigation.Navigator
import ui.navigation.di.NavigationModule
import ui.navigation.test.FakeNavigator

@Module(
    includes = [
        NavigationModule::class
    ]
)
class TestMainModule {

    @Provides
    @MainScope
    fun navigator(): Navigator {
        return FakeNavigator()
    }
}
