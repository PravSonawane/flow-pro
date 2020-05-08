package domain.flow.di

import core.lib.usecase.common.BasicUseCaseBuilder
import dagger.Module
import dagger.Provides
import domain.flow.usecases.get.flow.GetAllFlowsUseCase
import domain.flow.usecases.get.flow.GetAllFlowsUseCaseInternal
import domain.flow.usecases.get.flow.GetFlowByIdUseCase
import domain.flow.usecases.get.flow.GetFlowByIdUseCaseInternal
import domain.flow.usecases.get.node.GetAllNodesUseCase
import domain.flow.usecases.get.node.GetAllNodesUseCaseInternal
import domain.flow.usecases.get.step.GetAllStepsInput
import domain.flow.usecases.get.step.GetAllStepsUseCase
import domain.flow.usecases.get.step.GetAllStepsUseCaseInternal
import domain.flow.usecases.get.step.GetCurrentInputStepsUseCase
import domain.flow.usecases.get.step.GetCurrentInputStepsUseCaseInternal
import domain.flow.usecases.get.step.GetCurrentOutputStepsUseCase
import domain.flow.usecases.get.step.GetCurrentOutputStepsUseCaseInternal
import domain.flow.usecases.get.step.GetInputStepsInput
import domain.flow.usecases.get.step.GetOutputStepsInput
import domain.flow.usecases.get.step.GetPossibleInputStepsInput
import domain.flow.usecases.get.step.GetPossibleInputStepsUseCase
import domain.flow.usecases.get.step.GetPossibleInputStepsUseCaseInternal
import domain.flow.usecases.get.step.GetPossibleOutputStepsInput
import domain.flow.usecases.get.step.GetPossibleOutputStepsUseCase
import domain.flow.usecases.get.step.GetPossibleOutputStepsUseCaseInternal
import domain.flow.usecases.get.step.GetStepByIdUseCase
import domain.flow.usecases.get.step.GetStepByIdUseCaseInternal
import domain.flow.usecases.get.step.GetStepsInput
import domain.flow.usecases.get.step.GetStepsUseCase
import domain.flow.usecases.get.step.GetStepsUseCaseInternal
import domain.flow.usecases.save.flow.CreateFlowInput
import domain.flow.usecases.save.flow.CreateFlowUseCase
import domain.flow.usecases.save.flow.CreateFlowUseCaseInternal
import domain.flow.usecases.save.node.CreateNodeInput
import domain.flow.usecases.save.node.CreateNodeUseCase
import domain.flow.usecases.save.node.CreateNodeUseCaseInternal
import domain.models.flow.Flow
import domain.models.flow.Node
import domain.models.flow.Step

@Module
class FlowModule {

    @Provides
    fun provideGetAllFlows(
        builder: BasicUseCaseBuilder<Unit, List<Flow>>,
        getAllFlowsUseCase: GetAllFlowsUseCaseInternal
    ): GetAllFlowsUseCase {
        return builder.compose(getAllFlowsUseCase)
            .withAnalytics(GetAllFlowsUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(GetAllFlowsUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetAllNodes(
        builder: BasicUseCaseBuilder<Unit, List<Node>>,
        getAllNodesUseCase: GetAllNodesUseCaseInternal
    ): GetAllNodesUseCase {
        return builder.compose(getAllNodesUseCase)
            .withAnalytics(GetAllNodesUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(GetAllNodesUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetSteps(
        builder: BasicUseCaseBuilder<GetStepsInput, List<Step>>,
        getStepsUseCase: GetStepsUseCaseInternal
    ): GetStepsUseCase {
        return builder.compose(getStepsUseCase)
            .withAnalytics(GetStepsUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(GetStepsUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetAllSteps(
        builder: BasicUseCaseBuilder<GetAllStepsInput, List<Step>>,
        getAllStepsUseCase: GetAllStepsUseCaseInternal
    ): GetAllStepsUseCase {
        return builder.compose(getAllStepsUseCase)
            .withAnalytics(GetAllStepsUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(GetAllStepsUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetCurrentInputSteps(
        builder: BasicUseCaseBuilder<GetInputStepsInput, List<Step>>,
        getCurrentInputStepsUseCase: GetCurrentInputStepsUseCaseInternal
    ): GetCurrentInputStepsUseCase {
        return builder.compose(getCurrentInputStepsUseCase)
            .withAnalytics(GetCurrentInputStepsUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(GetCurrentInputStepsUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetCurrentOutputSteps(
        builder: BasicUseCaseBuilder<GetOutputStepsInput, List<Step>>,
        getCurrentOutputStepsUseCase: GetCurrentOutputStepsUseCaseInternal
    ): GetCurrentOutputStepsUseCase {
        return builder.compose(getCurrentOutputStepsUseCase)
            .withAnalytics(GetCurrentOutputStepsUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(GetCurrentOutputStepsUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetPossibleInputSteps(
        builder: BasicUseCaseBuilder<GetPossibleInputStepsInput, List<Step>>,
        getPossibleInputStepsUseCase: GetPossibleInputStepsUseCaseInternal
    ): GetPossibleInputStepsUseCase {
        return builder.compose(getPossibleInputStepsUseCase)
            .withAnalytics(GetPossibleInputStepsUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(GetPossibleInputStepsUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetPossibleOutputSteps(
        builder: BasicUseCaseBuilder<GetPossibleOutputStepsInput, List<Step>>,
        getPossibleOutputStepsUseCase: GetPossibleOutputStepsUseCaseInternal
    ): GetPossibleOutputStepsUseCase {
        return builder.compose(getPossibleOutputStepsUseCase)
            .withAnalytics(GetPossibleOutputStepsUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(GetPossibleOutputStepsUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetStepById(
        builder: BasicUseCaseBuilder<String, Step>,
        getStepByIdUseCase: GetStepByIdUseCaseInternal
    ): GetStepByIdUseCase {
        return builder.compose(getStepByIdUseCase)
            .withAnalytics(GetStepByIdUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(GetStepByIdUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetFlowById(
        builder: BasicUseCaseBuilder<String, Flow>,
        getFlowByIdUseCase: GetFlowByIdUseCaseInternal
    ): GetFlowByIdUseCase {
        return builder.compose(getFlowByIdUseCase)
            .withAnalytics(GetFlowByIdUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(GetFlowByIdUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideCreateFlow(
        builder: BasicUseCaseBuilder<CreateFlowInput, Flow>,
        createFlowUseCase: CreateFlowUseCase
    ): CreateFlowUseCase {
        return builder.compose(createFlowUseCase)
            .withAnalytics(CreateFlowUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(CreateFlowUseCaseInternal.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideCreateNode(
        builder: BasicUseCaseBuilder<CreateNodeInput, Node>,
        createNodeUseCase: CreateNodeUseCase
    ): CreateNodeUseCase {
        return builder.compose(createNodeUseCase)
            .withAnalytics(CreateNodeUseCaseInternal.ANALYTICS_KEY)
            .withPlugin(CreateNodeUseCaseInternal.PLUGIN_KEY)
            .build()
    }
}
