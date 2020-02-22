package data.flow.di

import core.lib.usecase.common.BusinessUseCase
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import domain.flow.repositories.StepRepository
import domain.flow.usecases.GetFlowByIdUseCase
import domain.models.flow.Flow
import javax.inject.Named

interface FlowComponent {

    @Named(GetFlowByIdUseCase.NAMED)
    fun getFlowById(): BusinessUseCase<String, Flow>

    fun flowRepository(): FlowRepository
    fun nodeRepository(): NodeRepository
    fun stepRepository(): StepRepository
}
