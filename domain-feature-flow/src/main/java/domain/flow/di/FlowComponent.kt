package domain.flow.di

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
import domain.flow.usecases.get.step.GetStepsUseCase

interface FlowComponent {

    fun getAllFlows(): GetAllFlowsUseCase

    fun getAllNodes(): GetAllNodesUseCase

    fun getFlowById(): GetFlowByIdUseCase

    fun getSteps(): GetStepsUseCase

    fun getStepById(): GetStepByIdUseCase

    fun getAllSteps(): GetAllStepsUseCase

    fun getCurrentInputSteps(): GetCurrentInputStepsUseCase

    fun getCurrentOutputSteps(): GetCurrentOutputStepsUseCase

    fun getPossibleInputSteps(): GetPossibleInputStepsUseCase

    fun getPossibleOutputSteps(): GetPossibleOutputStepsUseCase

    fun flowRepository(): FlowRepository
    fun nodeRepository(): NodeRepository
    fun stepRepository(): StepRepository
}
