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
        getAllFlowsFunction: GetAllFlowsFunction
    ): GetAllFlowsUseCase {
        return builder.compose(getAllFlowsFunction)
            .withAnalytics(GetAllFlowsFunction.ANALYTICS_KEY)
            .withPlugin(GetAllFlowsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetAllNodes(
        builder: BasicUseCaseBuilder<Unit, List<Node>>,
        getAllNodesFunction: GetAllNodesFunction
    ): GetAllNodesUseCase {
        return builder.compose(getAllNodesFunction)
            .withAnalytics(GetAllNodesFunction.ANALYTICS_KEY)
            .withPlugin(GetAllNodesFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetSteps(
        builder: BasicUseCaseBuilder<GetStepsInput, List<Step>>,
        getStepsFunction: GetStepsFunction
    ): GetStepsUseCase {
        return builder.compose(getStepsFunction)
            .withAnalytics(GetStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetAllSteps(
        builder: BasicUseCaseBuilder<GetAllStepsInput, List<Step>>,
        getAllStepsFunction: GetAllStepsFunction
    ): GetAllStepsUseCase {
        return builder.compose(getAllStepsFunction)
            .withAnalytics(GetAllStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetAllStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetCurrentInputSteps(
        builder: BasicUseCaseBuilder<GetInputStepsInput, List<Step>>,
        getCurrentInputStepsFunction: GetCurrentInputStepsFunction
    ): GetCurrentInputStepsUseCase {
        return builder.compose(getCurrentInputStepsFunction)
            .withAnalytics(GetCurrentInputStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetCurrentInputStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetCurrentOutputSteps(
        builder: BasicUseCaseBuilder<GetOutputStepsInput, List<Step>>,
        getCurrentOutputStepsFunction: GetCurrentOutputStepsFunction
    ): GetCurrentOutputStepsUseCase {
        return builder.compose(getCurrentOutputStepsFunction)
            .withAnalytics(GetCurrentOutputStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetCurrentOutputStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetPossibleInputSteps(
        builder: BasicUseCaseBuilder<GetPossibleInputStepsInput, List<Step>>,
        getPossibleInputStepsFunction: GetPossibleInputStepsFunction
    ): GetPossibleInputStepsUseCase {
        return builder.compose(getPossibleInputStepsFunction)
            .withAnalytics(GetPossibleInputStepsFunction.ANALYTICS_KEY)
            .withPlugin(GetPossibleInputStepsFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideGetPossibleOutputSteps(
        builder: BasicUseCaseBuilder<GetPossibleOutputStepsInput, List<Step>>,
        getPossibleOutputStepsFunction: GetPossibleOutputStepsFunction
    ): GetPossibleOutputStepsUseCase {
        return builder.compose(getPossibleOutputStepsFunction)
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
        getFlowByIdFunction: GetFlowByIdFunction
    ): GetFlowByIdUseCase {
        return builder.compose(getFlowByIdFunction)
            .withAnalytics(GetFlowByIdFunction.ANALYTICS_KEY)
            .withPlugin(GetFlowByIdFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideCreateFlow(
        builder: BasicUseCaseBuilder<CreateFlowInput, Flow>,
        createFlowFunction: CreateFlowFunction
    ): CreateFlowUseCase {
        return builder.compose(createFlowFunction)
            .withAnalytics(CreateFlowFunction.ANALYTICS_KEY)
            .withPlugin(CreateFlowFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideCreateNode(
        builder: BasicUseCaseBuilder<CreateNodeInput, Node>,
        createNodeFunction: CreateNodeFunction
    ): CreateNodeUseCase {
        return builder.compose(createNodeFunction)
            .withAnalytics(CreateNodeFunction.ANALYTICS_KEY)
            .withPlugin(CreateNodeFunction.PLUGIN_KEY)
            .build()
    }

    @Provides
    fun provideCreateStep(
        builder: BasicUseCaseBuilder<CreateStepInput, Step>,
        createStepFunction: CreateStepFunction
    ): CreateStepUseCase {
        return builder.compose(createStepFunction)
            .withAnalytics(CreateStepFunction.ANALYTICS_KEY)
            .withPlugin(CreateStepFunction.PLUGIN_KEY)
            .build()
    }
}
