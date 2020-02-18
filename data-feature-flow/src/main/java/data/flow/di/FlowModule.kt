package data.flow.di

import core.lib.analytics.AnalyticsRepository
import core.lib.plugin.PluginRepository
import core.lib.usecase.common.BusinessUseCase
import core.lib.usecase.common.di.CommonUseCaseModule
import dagger.Module
import dagger.Provides
import data.flow.repositories.DefaultFlowRepository
import data.flow.repositories.DefaultNodeRepository
import data.flow.repositories.DefaultStepRepository
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import domain.flow.repositories.StepRepository
import domain.flow.usecases.GetFlowByIdUseCase
import domain.models.flow.Flow
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [CommonUseCaseModule::class]
)
class FlowModule {

    @Singleton
    @Provides
    fun provideFlowRepository(): FlowRepository {
        return DefaultFlowRepository()
    }

    @Singleton
    @Provides
    fun provideNodeRepository(): NodeRepository {
        return DefaultNodeRepository()
    }

    @Singleton
    @Provides
    fun provideStepRepository(): StepRepository {
        return DefaultStepRepository()
    }

    @Named("GET_FLOW_BY_ID")
    @Provides
    fun getFlowById(
        flowRepository: FlowRepository,
        pluginRepository: PluginRepository,
        analyticsRepository: AnalyticsRepository
    ): BusinessUseCase<String, Flow> {
        return BusinessUseCase(
            GetFlowByIdUseCase(flowRepository),
            pluginRepository,
            analyticsRepository
        )
    }
}
