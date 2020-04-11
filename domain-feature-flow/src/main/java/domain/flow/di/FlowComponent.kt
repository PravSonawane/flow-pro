package domain.flow.di

import core.lib.usecase.ObservableResultUseCase
import core.lib.usecase.common.BusinessUseCase
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import domain.flow.repositories.StepRepository
import domain.flow.usecases.GetAllFlowsUseCase
import domain.flow.usecases.GetAllStepsInput
import domain.flow.usecases.GetAllStepsUseCase
import domain.flow.usecases.GetCurrentInputStepsUseCase
import domain.flow.usecases.GetCurrentOutputStepsUseCase
import domain.flow.usecases.GetFlowByIdUseCase
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

interface FlowComponent {

    @Named(GetAllFlowsUseCase.NAMED)
    fun getAllFlows(): ObservableResultUseCase<Unit, List<Flow>>

    fun getFlowById(): GetFlowByIdUseCase

    @Named(GetStepsUseCase.NAMED)
    fun getSteps(): ObservableResultUseCase<GetStepsInput, List<Step>>

    @Named(GetStepByIdUseCase.NAMED)
    fun getStepById(): BusinessUseCase<String, Step>

    @Named(GetStepByIdUseCase.NAMED_V2)
    fun getStepByIdV2(): ObservableResultUseCase<String, Step>

    @Named(GetAllStepsUseCase.NAMED)
    fun getAllSteps(): ObservableResultUseCase<GetAllStepsInput, List<Step>>

    @Named(GetCurrentInputStepsUseCase.NAMED)
    fun getCurrentInputSteps(): BusinessUseCase<GetInputStepsInput, List<Step>>

    @Named(GetCurrentOutputStepsUseCase.NAMED)
    fun getCurrentOutputSteps(): BusinessUseCase<GetOutputStepsInput, List<Step>>

    @Named(GetPossibleInputStepsUseCase.NAMED)
    fun getPossibleInputSteps(): BusinessUseCase<GetPossibleInputStepsInput, List<Step>>

    @Named(GetPossibleOutputStepsUseCase.NAMED)
    fun getPossibleOutputSteps(): BusinessUseCase<GetPossibleOutputStepsInput, List<Step>>

    fun flowRepository(): FlowRepository
    fun nodeRepository(): NodeRepository
    fun stepRepository(): StepRepository
}
