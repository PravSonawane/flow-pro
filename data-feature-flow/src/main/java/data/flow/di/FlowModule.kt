package data.flow.di

import core.lib.usecase.common.BusinessUseCase
import core.lib.usecase.common.BusinessUseCaseFactory
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
import domain.flow.usecases.GetCurrentInputStepsUseCase
import domain.flow.usecases.GetOutputStepsInput
import domain.flow.usecases.GetOutputStepsUseCase
import domain.flow.usecases.GetStepByIdUseCase
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
    fun provideGetFlowById(
        factory: BusinessUseCaseFactory<String, Flow>,
        getFlowByIdUseCase: GetFlowByIdUseCase
    ): BusinessUseCase<String, Flow> {
        return factory.create(getFlowByIdUseCase)
    }

    @Named(GetCurrentInputStepsUseCase.NAMED)
    @Provides
    fun provideGetInputSteps(
        factory: BusinessUseCaseFactory<GetInputStepsInput, List<Step>>,
        getCurrentInputStepsUseCase: GetCurrentInputStepsUseCase
    ): BusinessUseCase<GetInputStepsInput, List<Step>> {
        return factory.create(getCurrentInputStepsUseCase)
    }

    @Named(GetOutputStepsUseCase.NAMED)
    @Provides
    fun provideGetOutputSteps(
        factory: BusinessUseCaseFactory<GetOutputStepsInput, List<Step>>,
        getOutputStepsUseCase: GetOutputStepsUseCase
    ): BusinessUseCase<GetOutputStepsInput, List<Step>> {
        return factory.create(getOutputStepsUseCase)
    }

    @Named(GetStepByIdUseCase.NAMED)
    @Provides
    fun provideGetStepById(
        factory: BusinessUseCaseFactory<String, Step>,
        getStepByIdUseCase: GetStepByIdUseCase
    ): BusinessUseCase<String, Step> {
        return factory.create(getStepByIdUseCase)
    }
}
