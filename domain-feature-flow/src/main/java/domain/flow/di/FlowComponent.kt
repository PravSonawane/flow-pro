package domain.flow.di

import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import domain.flow.repositories.StepRepository
import domain.flow.usecases.get.flow.GetAllFlowsUseCase
import domain.flow.usecases.get.flow.GetFlowByIdUseCase
import domain.flow.usecases.get.node.GetAllNodesUseCase
import domain.flow.usecases.get.step.GetAllStepsUseCase
import domain.flow.usecases.get.step.GetCurrentInputStepsUseCase
import domain.flow.usecases.get.step.GetCurrentOutputStepsUseCase
import domain.flow.usecases.get.step.GetPossibleInputStepsUseCase
import domain.flow.usecases.get.step.GetPossibleOutputStepsUseCase
import domain.flow.usecases.get.step.GetStepByIdUseCase
import domain.flow.usecases.get.step.GetStepsInput
import domain.flow.usecases.get.step.GetStepsUseCase
import domain.models.flow.Flow
import domain.models.flow.Node
import domain.models.flow.Step
import javax.inject.Named

interface FlowComponent {

    fun getAllFlows(): GetAllFlowsUseCase

    @Named(GetAllNodesUseCase.NAMED)
    fun getAllNodes(): ObservableResultUseCase<Unit, List<Node>>

    @Named(GetFlowByIdUseCase.NAMED)
    fun getFlowById(): ObservableResultUseCase<String, Flow>

    @Named(GetStepsUseCase.NAMED)
    fun getSteps(): ObservableResultUseCase<GetStepsInput, List<Step>>

    @Named(GetStepByIdUseCase.NAMED)
    fun getStepById(): ObservableResultUseCase<String, Step>

    fun getAllSteps(): GetAllStepsUseCase

    fun getCurrentInputSteps(): GetCurrentInputStepsUseCase

    fun getCurrentOutputSteps(): GetCurrentOutputStepsUseCase

    fun getPossibleInputSteps(): GetPossibleInputStepsUseCase

    fun getPossibleOutputSteps(): GetPossibleOutputStepsUseCase

    fun flowRepository(): FlowRepository
    fun nodeRepository(): NodeRepository
    fun stepRepository(): StepRepository
}
