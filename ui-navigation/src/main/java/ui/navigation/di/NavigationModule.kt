package ui.navigation.di

import core.lib.usecase.ObservableResultUseCase
import core.lib.usecase.common.BasicUseCaseBuilder
import dagger.Provides
import ui.navigation.NavigationConfig
import ui.navigation.usecases.LogNavigationUseCase
import javax.inject.Named

@dagger.Module
class NavigationModule {

    @Named(LogNavigationUseCase.NAMED)
    @Provides
    fun provideLogNavigation(
        builder: BasicUseCaseBuilder<NavigationConfig, NavigationConfig>,
        useCase: LogNavigationUseCase
    ): ObservableResultUseCase<NavigationConfig, NavigationConfig> {
        return builder.compose(useCase)
            .withAnalytics(LogNavigationUseCase.ANALYTICS_KEY)
            .withPlugin(LogNavigationUseCase.PLUGIN_KEY)
            .build()
    }
}