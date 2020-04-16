package app.di

import android.app.Activity
import androidx.annotation.IntegerRes
import app.di.annotations.ActivityContext
import app.di.annotations.NavHostResourceId
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
    @IntegerRes private val navHostViewId: Int
) {

    @Provides
    @ActivityScope
    @NavHostResourceId
    fun provideNavHostResId(): Int {
        return navHostViewId
    }

    @Provides
    @ActivityScope
    fun provideNavigator(
        @ActivityContext activity: Activity,
        @NavHostResourceId navHostViewId: Int,
        @Named(LogNavigationUseCase.NAMED)
        useCase: ObservableResultUseCase<NavigationConfig, NavigationConfig>
    ): Navigator {
        return SimpleNavigator(activity, navHostViewId, useCase)
    }
}
