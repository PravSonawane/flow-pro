package domain.flow.di

import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import domain.flow.repositories.StepRepository
import domain.flow.usecases.get.flow.GetAllFlowsUseCase
import domain.flow.usecases.get.node.GetAllNodesUseCase
import domain.flow.usecases.get.step.GetAllStepsInput
import domain.flow.usecases.get.step.GetAllStepsUseCase
import domain.flow.usecases.get.step.GetCurrentInputStepsUseCase
import domain.flow.usecases.GetCurrentOutputStepsUseCase
import domain.flow.usecases.get.flow.GetFlowByIdUseCase
import domain.flow.usecases.get.step.GetInputStepsInput
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

interface FlowComponent {

    @Named(GetAllFlowsUseCase.NAMED)
    fun getAllFlows(): ObservableResultUseCase<Unit, List<Flow>>

    @Named(GetAllNodesUseCase.NAMED)
    fun getAllNodes(): ObservableResultUseCase<Unit, List<Node>>

    @Named(GetFlowByIdUseCase.NAMED)
    fun getFlowById(): ObservableResultUseCase<String, Flow>

    @Named(GetStepsUseCase.NAMED)
    fun getSteps(): ObservableResultUseCase<GetStepsInput, List<Step>>

    @Named(GetStepByIdUseCase.NAMED)
    fun getStepById(): ObservableResultUseCase<String, Step>

    @Named(GetAllStepsUseCase.NAMED)
    fun getAllSteps(): ObservableResultUseCase<GetAllStepsInput, List<Step>>

    @Named(GetCurrentInputStepsUseCase.NAMED)
    fun getCurrentInputSteps(): ObservableResultUseCase<GetInputStepsInput, List<Step>>

    @Named(GetCurrentOutputStepsUseCase.NAMED)
    fun getCurrentOutputSteps(): ObservableResultUseCase<GetOutputStepsInput, List<Step>>

    @Named(GetPossibleInputStepsUseCase.NAMED)
    fun getPossibleInputSteps(): ObservableResultUseCase<GetPossibleInputStepsInput, List<Step>>

    @Named(GetPossibleOutputStepsUseCase.NAMED)
    fun getPossibleOutputSteps(): ObservableResultUseCase<GetPossibleOutputStepsInput, List<Step>>

    fun flowRepository(): FlowRepository
    fun nodeRepository(): NodeRepository
    fun stepRepository(): StepRepository
}
