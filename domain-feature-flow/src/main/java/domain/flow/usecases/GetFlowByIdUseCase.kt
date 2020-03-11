package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.common.AbstractBusinessUseCase
import core.lib.usecase.common.BusinessData
import domain.flow.repositories.FlowRepository
import domain.models.flow.Flow
import io.reactivex.Observable

abstract class GetFlowByIdUseCase(
    private val flowRepository: FlowRepository
) : AbstractBusinessUseCase<String, Flow>() {

    override operator fun invoke(input: BusinessData<String>): Observable<Result<Flow>> {
        return flowRepository.get(input.data)
    }

    companion object {
        const val NAMED = "GET_FLOW_BY_ID"
    }
}
