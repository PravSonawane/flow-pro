package domain.flow.di

import core.lib.usecase.ObservableResultUseCase
import core.lib.usecase.common.BasicUseCaseBuilder
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
import domain.flow.usecases.GetStepsInput
import domain.flow.usecases.GetStepsUseCase
import domain.models.flow.Flow
import domain.models.flow.Step
import javax.inject.Named

@Module(includes = [AbstractFlowModule::class])
class FlowModule {

    @Named(GetAllFlowsUseCase.NAMED)
    @Provides
    fun provideGetAllFlows(
        builder: BasicUseCaseBuilder<Unit, List<Flow>>,
        getAllFlowsUseCase: GetAllFlowsUseCase
    ): ObservableResultUseCase<Unit, List<Flow>> {
        return builder.compose(getAllFlowsUseCase)
            .withAnalytics(GetAllFlowsUseCase.ANALYTICS_KEY)
            .withPlugin(GetAllFlowsUseCase.PLUGIN_KEY)
            .build()
    }

    @Named(GetStepsUseCase.NAMED)
    @Provides
    fun provideGetSteps(
        builder: BasicUseCaseBuilder<GetStepsInput, List<Step>>,
        getStepsUseCase: GetStepsUseCase
    ): ObservableResultUseCase<GetStepsInput, List<Step>> {
        return builder.compose(getStepsUseCase)
            .withAnalytics(GetStepsUseCase.ANALYTICS_KEY)
            .withPlugin(GetStepsUseCase.PLUGIN_KEY)
            .build()
    }

    @Named(GetAllStepsUseCase.NAMED)
    @Provides
    fun provideGetAllSteps(
        builder: BasicUseCaseBuilder<GetAllStepsInput, List<Step>>,
        getAllStepsUseCase: GetAllStepsUseCase
    ): ObservableResultUseCase<GetAllStepsInput, List<Step>> {
        return builder.compose(getAllStepsUseCase)
            .withAnalytics(GetAllStepsUseCase.ANALYTICS_KEY)
            .withPlugin(GetAllStepsUseCase.PLUGIN_KEY)
            .build()
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

    @Named(GetStepByIdUseCase.NAMED_V2)
    @Provides
    fun provideGetStepByIdV2(
        builder: BasicUseCaseBuilder<String, Step>,
        getStepByIdUseCase: GetStepByIdUseCase
    ): ObservableResultUseCase<String, Step> {
        return builder.compose(getStepByIdUseCase)
            .withAnalytics("98301cab-9995")
            .withPlugin("8a35d450-f99b")
            .build()
    }
}
