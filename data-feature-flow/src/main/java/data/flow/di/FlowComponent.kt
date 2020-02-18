package data.flow.di

import core.lib.usecase.common.BusinessUseCase
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import domain.flow.repositories.StepRepository
import domain.models.flow.Flow
import javax.inject.Named

interface FlowComponent {

    @Named("GET_FLOW_BY_ID")
    fun getFlowById(): BusinessUseCase<String, Flow>

    fun flowRepository(): FlowRepository
    fun nodeRepository(): NodeRepository
    fun stepRepository(): StepRepository
}
