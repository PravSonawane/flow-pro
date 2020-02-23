package data.flow.di

import core.lib.usecase.common.*
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
import domain.flow.usecases.GetInputStepsInput
import domain.flow.usecases.GetInputStepsUseCase
import domain.models.flow.Flow
import domain.models.flow.Step
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

    @Named(GetFlowByIdUseCase.NAMED)
    @Provides
    fun getFlowById(
        factory: BusinessUseCaseFactory<String, Flow>,
        getFlowByIdUseCase: GetFlowByIdUseCase
    ): BusinessUseCase<String, Flow> {
        return factory.create(getFlowByIdUseCase)
    }

    @Named(GetInputStepsUseCase.NAMED)
    @Provides
    fun getInputSteps(
        factory: BusinessUseCaseFactory<GetInputStepsInput, List<Step>>,
        getInputStepsUseCase: GetInputStepsUseCase
    ): BusinessUseCase<GetInputStepsInput, List<Step>> {
        return factory.create(getInputStepsUseCase)
    }
}
