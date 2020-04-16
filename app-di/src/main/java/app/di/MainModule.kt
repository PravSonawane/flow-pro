package app.di

import dagger.Binds
import dagger.Module
import ui.lib.di.ActivityScope
import ui.navigation.Navigator
import ui.navigation.SimpleNavigator
import ui.navigation.di.NavigationModule

@Module(
    includes = [
        NavigationModule::class
    ]
)
abstract class MainModule {

    @Binds
    @ActivityScope
    abstract fun provideNavigator(simpleNavigator: SimpleNavigator): Navigator
}
