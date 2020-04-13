package app.di

import android.app.Activity
import androidx.annotation.IntegerRes
import app.di.annotations.ActivityContext
import core.lib.usecase.ObservableResultUseCase
import dagger.Module
import dagger.Provides
import ui.lib.di.ActivityScope
import ui.navigation.NavigationConfig
import ui.navigation.Navigator
import ui.navigation.SimpleNavigator
import ui.navigation.di.NavigationModule
import ui.navigation.usecases.LogNavigationUseCase
import javax.inject.Named

@Module(
    includes = [
        NavigationModule::class
    ]
)
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
    fun navigator(
        @ActivityContext activity: Activity,
        @Named(LogNavigationUseCase.NAMED)
        logNavigationUseCase: ObservableResultUseCase<NavigationConfig, NavigationConfig>
    ): Navigator {
        return SimpleNavigator(activity, navHostViewId, logNavigationUseCase)
    }
}
