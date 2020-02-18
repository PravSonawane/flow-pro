package core.lib.usecase.common.di

import core.lib.analytics.AnalyticsRepository
import core.lib.plugin.PluginRepository
import core.lib.usecase.ObservableResultUseCase
import core.lib.usecase.common.AnalyticsUseCase
import core.lib.usecase.common.PluginUseCase
import dagger.Module
import dagger.Provides

@Module
class CommonUseCaseModule {

    @Provides
    fun analyticsUseCase(
        useCase: ObservableResultUseCase<*, *>,
        analyticsRepository: AnalyticsRepository
    ): AnalyticsUseCase<*, *> {
        return AnalyticsUseCase(useCase, analyticsRepository)
    }

    @Provides
    fun pluginUseCase(
        useCase: AnalyticsUseCase<*, *>,
        analyticsRepository: AnalyticsRepository,
        pluginRepository: PluginRepository
    ): PluginUseCase<*, *> {
        return PluginUseCase(useCase, analyticsRepository, pluginRepository)
    }
}