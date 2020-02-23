package data.flow.di

import core.lib.usecase.common.BusinessUseCase
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import domain.flow.repositories.StepRepository
import domain.flow.usecases.GetFlowByIdUseCase
import domain.flow.usecases.GetInputStepsInput
import domain.flow.usecases.GetInputStepsUseCase
import domain.models.flow.Flow
import domain.models.flow.Step
import javax.inject.Named

interface FlowComponent {

    @Named(GetFlowByIdUseCase.NAMED)
    fun getFlowById(): BusinessUseCase<String, Flow>

    @Named(GetInputStepsUseCase.NAMED)
    fun getInputSteps(): BusinessUseCase<GetInputStepsInput, List<Step>>

    fun flowRepository(): FlowRepository
    fun nodeRepository(): NodeRepository
    fun stepRepository(): StepRepository
}
