package domain.flow.di

import core.lib.usecase.ObservableResultUseCase
import core.lib.usecase.common.BasicUseCaseBuilder
import dagger.Module
import dagger.Provides
import domain.flow.usecases.get.flow.GetAllFlowsUseCase
import domain.flow.usecases.get.node.GetAllNodesUseCase
import domain.flow.usecases.GetAllStepsInput
import domain.flow.usecases.GetAllStepsUseCase
import domain.flow.usecases.GetCurrentInputStepsUseCase
import domain.flow.usecases.GetCurrentOutputStepsUseCase
import domain.flow.usecases.get.flow.GetFlowByIdUseCase
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
import domain.models.flow.Node
import domain.models.flow.Step
import javax.inject.Named

@Module
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

    @Named(GetAllNodesUseCase.NAMED)
    @Provides
    fun provideGetAllNodes(
        builder: BasicUseCaseBuilder<Unit, List<Node>>,
        getAllNodesUseCase: GetAllNodesUseCase
    ): ObservableResultUseCase<Unit, List<Node>> {
        return builder.compose(getAllNodesUseCase)
            .withAnalytics(GetAllNodesUseCase.ANALYTICS_KEY)
            .withPlugin(GetAllNodesUseCase.PLUGIN_KEY)
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
        builder: BasicUseCaseBuilder<GetInputStepsInput, List<Step>>,
        getCurrentInputStepsUseCase: GetCurrentInputStepsUseCase
    ): ObservableResultUseCase<GetInputStepsInput, List<Step>> {
        return builder.compose(getCurrentInputStepsUseCase)
            .withAnalytics(GetCurrentInputStepsUseCase.ANALYTICS_KEY)
            .withPlugin(GetCurrentInputStepsUseCase.PLUGIN_KEY)
            .build()
    }

    @Named(GetCurrentOutputStepsUseCase.NAMED)
    @Provides
    fun provideGetCurrentOutputSteps(
        builder: BasicUseCaseBuilder<GetOutputStepsInput, List<Step>>,
        getCurrentOutputStepsUseCase: GetCurrentOutputStepsUseCase
    ): ObservableResultUseCase<GetOutputStepsInput, List<Step>> {
        return builder.compose(getCurrentOutputStepsUseCase)
            .withAnalytics(GetCurrentOutputStepsUseCase.ANALYTICS_KEY)
            .withPlugin(GetCurrentOutputStepsUseCase.PLUGIN_KEY)
            .build()
    }

    @Named(GetPossibleInputStepsUseCase.NAMED)
    @Provides
    fun provideGetPossibleInputSteps(
        builder: BasicUseCaseBuilder<GetPossibleInputStepsInput, List<Step>>,
        getPossibleInputStepsUseCase: GetPossibleInputStepsUseCase
    ): ObservableResultUseCase<GetPossibleInputStepsInput, List<Step>> {
        return builder.compose(getPossibleInputStepsUseCase)
            .withAnalytics(GetPossibleInputStepsUseCase.ANALYTICS_KEY)
            .withPlugin(GetPossibleInputStepsUseCase.PLUGIN_KEY)
            .build()
    }

    @Named(GetPossibleOutputStepsUseCase.NAMED)
    @Provides
    fun provideGetPossibleOutputSteps(
        builder: BasicUseCaseBuilder<GetPossibleOutputStepsInput, List<Step>>,
        getPossibleOutputStepsUseCase: GetPossibleOutputStepsUseCase
    ): ObservableResultUseCase<GetPossibleOutputStepsInput, List<Step>> {
        return builder.compose(getPossibleOutputStepsUseCase)
            .withAnalytics(GetPossibleOutputStepsUseCase.ANALYTICS_KEY)
            .withPlugin(GetPossibleOutputStepsUseCase.PLUGIN_KEY)
            .build()
    }

    @Named(GetStepByIdUseCase.NAMED)
    @Provides
    fun provideGetStepById(
        builder: BasicUseCaseBuilder<String, Step>,
        getStepByIdUseCase: GetStepByIdUseCase
    ): ObservableResultUseCase<String, Step> {
        return builder.compose(getStepByIdUseCase)
            .withAnalytics(GetStepByIdUseCase.ANALYTICS_KEY)
            .withPlugin(GetStepByIdUseCase.PLUGIN_KEY)
            .build()
    }

    @Named(GetFlowByIdUseCase.NAMED)
    @Provides
    fun provideGetFlowById(
        builder: BasicUseCaseBuilder<String, Flow>,
        getFlowByIdUseCase: GetFlowByIdUseCase
    ): ObservableResultUseCase<String, Flow> {
        return builder.compose(getFlowByIdUseCase)
            .withAnalytics(GetFlowByIdUseCase.ANALYTICS_KEY)
            .withPlugin(GetFlowByIdUseCase.PLUGIN_KEY)
            .build()
    }
}
