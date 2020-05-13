package domain.flow.di

import core.lib.usecase.common.BasicUseCaseBuilder
import dagger.Module
import dagger.Provides
import domain.flow.usecases.get.flow.GetAllFlowsUseCase
import domain.flow.usecases.get.flow.GetAllFlowsFunction
import domain.flow.usecases.get.flow.GetFlowByIdUseCase
import domain.flow.usecases.get.flow.GetFlowByIdFunction
import domain.flow.usecases.get.node.GetAllNodesUseCase
import domain.flow.usecases.get.node.GetAllNodesFunction
import domain.flow.usecases.get.step.GetAllStepsInput
import domain.flow.usecases.get.step.GetAllStepsUseCase
import domain.flow.usecases.get.step.GetAllStepsFunction
import domain.flow.usecases.get.step.GetCurrentInputStepsUseCase
import domain.flow.usecases.get.step.GetCurrentInputStepsFunction
import domain.flow.usecases.get.step.GetCurrentOutputStepsUseCase
import domain.flow.usecases.get.step.GetCurrentOutputStepsFunction
import domain.flow.usecases.get.step.GetInputStepsInput
import domain.flow.usecases.get.step.GetOutputStepsInput
import domain.flow.usecases.get.step.GetPossibleInputStepsInput
import domain.flow.usecases.get.step.GetPossibleInputStepsUseCase
import domain.flow.usecases.get.step.GetPossibleInputStepsFunction
import domain.flow.usecases.get.step.GetPossibleOutputStepsInput
import domain.flow.usecases.get.step.GetPossibleOutputStepsUseCase
import domain.flow.usecases.get.step.GetPossibleOutputStepsFunction
import domain.flow.usecases.get.step.GetStepByIdUseCase
import domain.flow.usecases.get.step.GetStepByIdFunction
import domain.flow.usecases.get.step.GetStepsInput
import domain.flow.usecases.get.step.GetStepsUseCase
import domain.flow.usecases.get.step.GetStepsFunction
import domain.flow.usecases.save.flow.CreateFlowInput
import domain.flow.usecases.save.flow.CreateFlowUseCase
import domain.flow.usecases.save.flow.CreateFlowFunction
import domain.flow.usecases.save.node.CreateNodeInput
import domain.flow.usecases.save.node.CreateNodeUseCase
import domain.flow.usecases.save.node.CreateNodeFunction
import domain.flow.usecases.save.step.CreateStepInput
import domain.flow.usecases.save.step.CreateStepUseCase
import domain.flow.usecases.save.step.CreateStepFunction
import domain.models.flow.Flow
import domain.models.flow.Node
import domain.models.flow.Step

@Module
class FlowModule {

    @Provides
    fun provideGetAllFlows(
        builder: BasicUseCaseBuilder<Unit, List<Flow>>,
        getAllFlowsUseCase: GetAllFlowsFunction
    ): GetAllFlowsUseCase {
        return builder.compose(getAllFlowsUseCase)
            .withAnalytics(GetAllFlowsFunction.ANALYTICS_KEY)
            .withPlugin(GetAllFlowsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetAllNodes(
        builder: BasicUseCaseBuilder<Unit, List<Node>>,
        getAllNodesUseCase: GetAllNodesFunction
    ): GetAllNodesUseCase {
        return builder.compose(getAllNodesUseCase)
            .withAnalytics(GetAllNodesFunction.ANALYTICS_KEY)
            .withPlugin(GetAllNodesFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetSteps(
        builder: BasicUseCaseBuilder<GetStepsInput, List<Step>>,
        getStepsUseCase: GetStepsFunction
    ): GetStepsUseCase {
        return builder.compose(getStepsUseCase)
            .withAnalytics(GetStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetAllSteps(
        builder: BasicUseCaseBuilder<GetAllStepsInput, List<Step>>,
        getAllStepsUseCase: GetAllStepsFunction
    ): GetAllStepsUseCase {
        return builder.compose(getAllStepsUseCase)
            .withAnalytics(GetAllStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetAllStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetCurrentInputSteps(
        builder: BasicUseCaseBuilder<GetInputStepsInput, List<Step>>,
        getCurrentInputStepsUseCase: GetCurrentInputStepsFunction
    ): GetCurrentInputStepsUseCase {
        return builder.compose(getCurrentInputStepsUseCase)
            .withAnalytics(GetCurrentInputStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetCurrentInputStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetCurrentOutputSteps(
        builder: BasicUseCaseBuilder<GetOutputStepsInput, List<Step>>,
        getCurrentOutputStepsUseCase: GetCurrentOutputStepsFunction
    ): GetCurrentOutputStepsUseCase {
        return builder.compose(getCurrentOutputStepsUseCase)
            .withAnalytics(GetCurrentOutputStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetCurrentOutputStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetPossibleInputSteps(
        builder: BasicUseCaseBuilder<GetPossibleInputStepsInput, List<Step>>,
        getPossibleInputStepsUseCase: GetPossibleInputStepsFunction
    ): GetPossibleInputStepsUseCase {
        return builder.compose(getPossibleInputStepsUseCase)
            .withAnalytics(GetPossibleInputStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetPossibleInputStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetPossibleOutputSteps(
        builder: BasicUseCaseBuilder<GetPossibleOutputStepsInput, List<Step>>,
        getPossibleOutputStepsUseCase: GetPossibleOutputStepsFunction
    ): GetPossibleOutputStepsUseCase {
        return builder.compose(getPossibleOutputStepsUseCase)
            .withAnalytics(GetPossibleOutputStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetPossibleOutputStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetStepById(
        builder: BasicUseCaseBuilder<String, Step>,
        getStepByIdUseCase: GetStepByIdFunction
    ): GetStepByIdUseCase {
        return builder.compose(getStepByIdUseCase)
            .withAnalytics(GetStepByIdFunction.ANALYTICS_KEY)
            .withPlugin(GetStepByIdFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetFlowById(
        builder: BasicUseCaseBuilder<String, Flow>,
        getFlowByIdUseCase: GetFlowByIdFunction
    ): GetFlowByIdUseCase {
        return builder.compose(getFlowByIdUseCase)
            .withAnalytics(GetFlowByIdFunction.ANALYTICS_KEY)
            .withPlugin(GetFlowByIdFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideCreateFlow(
        builder: BasicUseCaseBuilder<CreateFlowInput, Flow>,
        createFlowUseCase: CreateFlowFunction
    ): CreateFlowUseCase {
        return builder.compose(createFlowUseCase)
            .withAnalytics(CreateFlowFunction.ANALYTICS_KEY)
            .withPlugin(CreateFlowFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideCreateNode(
        builder: BasicUseCaseBuilder<CreateNodeInput, Node>,
        createNodeUseCase: CreateNodeFunction
    ): CreateNodeUseCase {
        return builder.compose(createNodeUseCase)
            .withAnalytics(CreateNodeFunction.ANALYTICS_KEY)
            .withPlugin(CreateNodeFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideCreateStep(
        builder: BasicUseCaseBuilder<CreateStepInput, Step>,
        createStepUseCase: CreateStepFunction
    ): CreateStepUseCase {
        return builder.compose(createStepUseCase)
            .withAnalytics(CreateStepFunction.ANALYTICS_KEY)
            .withPlugin(CreateStepFunction.PLUGIN_KEY)
            .build()
    }
}
