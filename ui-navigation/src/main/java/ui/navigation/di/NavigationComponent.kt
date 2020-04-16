package ui.navigation.di

import core.lib.usecase.ObservableResultUseCase
import ui.navigation.NavigationConfig
import ui.navigation.usecases.NavigationUseCase
import javax.inject.Named

interface NavigationComponent {

    @Named(NavigationUseCase.NAMED)
    fun navigationUseCase(): ObservableResultUseCase<NavigationConfig, String>
}