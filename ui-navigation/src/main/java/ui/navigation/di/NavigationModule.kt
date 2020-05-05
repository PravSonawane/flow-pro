package ui.navigation.di

import core.lib.usecase.ObservableResultUseCase
import core.lib.usecase.common.BasicUseCaseBuilder
import dagger.Provides
import ui.navigation.NavigationConfig
import ui.navigation.usecases.NavigationUseCase
import javax.inject.Named

@dagger.Module
class NavigationModule {

    @Named(NavigationUseCase.NAMED)
    @Provides
    fun provideLogNavigation(
        builder: BasicUseCaseBuilder<NavigationConfig, String>,
        useCase: NavigationUseCase
    ): ObservableResultUseCase<NavigationConfig, String> {
        return builder.compose(useCase)
            .withAnalytics(NavigationUseCase.ANALYTICS_KEY)
            .withPlugin(NavigationUseCase.PLUGIN_KEY)
            .build()
    }
}
