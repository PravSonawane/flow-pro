package domain.flow.di

import core.lib.usecase.common.BusinessUseCase
import core.lib.usecase.common.BusinessUseCaseFactory
import dagger.Module
import dagger.Provides
import domain.flow.usecases.GetAllFlowsUseCase
import domain.flow.usecases.GetAllStepsInput
import domain.flow.usecases.GetAllStepsUseCase
import domain.flow.usecases.GetCurrentInputStepsUseCase
import domain.flow.usecases.GetCurrentOutputStepsUseCase
import domain.flow.usecases.GetInputStepsInput
import domain.flow.usecases.GetOutputStepsInput
import domain.flow.usecases.GetPossibleInputStepsInput
import domain.flow.usecases.GetPossibleInputStepsUseCase
import domain.flow.usecases.GetPossibleOutputStepsInput
import domain.flow.usecases.GetPossibleOutputStepsUseCase
import domain.flow.usecases.GetStepByIdUseCase
import domain.models.flow.Flow
import domain.models.flow.Step
import javax.inject.Named

@Module(includes = [AbstractFlowModule::class])
class FlowModule {

    @Named(GetAllFlowsUseCase.NAMED)
    @Provides
    fun provideGetAllFlows(
        factory: BusinessUseCaseFactory<Unit, List<Flow>>,
        getAllFlowsUseCase: GetAllFlowsUseCase
    ): BusinessUseCase<Unit, List<Flow>> {
        return factory.create(getAllFlowsUseCase)
    }

    @Named(GetAllStepsUseCase.NAMED)
    @Provides
    fun provideGetAllSteps(
        factory: BusinessUseCaseFactory<GetAllStepsInput, List<Step>>,
        getAllStepsUseCase: GetAllStepsUseCase
    ): BusinessUseCase<GetAllStepsInput, List<Step>> {
        return factory.create(getAllStepsUseCase)
    }

    @Named(GetCurrentInputStepsUseCase.NAMED)
    @Provides
    fun provideGetCurrentInputSteps(
        factory: BusinessUseCaseFactory<GetInputStepsInput, List<Step>>,
        getCurrentInputStepsUseCase: GetCurrentInputStepsUseCase
    ): BusinessUseCase<GetInputStepsInput, List<Step>> {
        return factory.create(getCurrentInputStepsUseCase)
    }

    @Named(GetCurrentOutputStepsUseCase.NAMED)
    @Provides
    fun provideGetCurrentOutputSteps(
        factory: BusinessUseCaseFactory<GetOutputStepsInput, List<Step>>,
        getCurrentOutputStepsUseCase: GetCurrentOutputStepsUseCase
    ): BusinessUseCase<GetOutputStepsInput, List<Step>> {
        return factory.create(getCurrentOutputStepsUseCase)
    }

    @Named(GetPossibleInputStepsUseCase.NAMED)
    @Provides
    fun provideGetPossibleInputSteps(
        factory: BusinessUseCaseFactory<GetPossibleInputStepsInput, List<Step>>,
        getPossibleInputStepsUseCase: GetPossibleInputStepsUseCase
    ): BusinessUseCase<GetPossibleInputStepsInput, List<Step>> {
        return factory.create(getPossibleInputStepsUseCase)
    }

    @Named(GetPossibleOutputStepsUseCase.NAMED)
    @Provides
    fun provideGetPossibleOutputSteps(
        factory: BusinessUseCaseFactory<GetPossibleOutputStepsInput, List<Step>>,
        getPossibleOutputStepsUseCase: GetPossibleOutputStepsUseCase
    ): BusinessUseCase<GetPossibleOutputStepsInput, List<Step>> {
        return factory.create(getPossibleOutputStepsUseCase)
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
