package domain.flow.di

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
import domain.flow.usecases.save.flow.CreateFlowUseCase
import domain.flow.usecases.save.node.CreateNodeUseCase
import domain.flow.usecases.save.step.CreateStepUseCase

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

    fun createFlowUseCase(): CreateFlowUseCase

    fun createStepUseCase(): CreateStepUseCase

    fun createNodeUseCase(): CreateNodeUseCase
}
