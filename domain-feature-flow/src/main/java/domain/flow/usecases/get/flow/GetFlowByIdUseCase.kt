package domain.flow.usecases.get.flow

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.FlowRepository
import domain.models.flow.Flow
import io.reactivex.Observable
import javax.inject.Inject

class GetFlowByIdUseCase @Inject constructor(
    private val flowRepository: FlowRepository
) : ObservableResultUseCase<String, Flow> {

    override operator fun invoke(input: String): Observable<Result<Flow>> {
        return flowRepository.get(input)
    }

    companion object {
        const val NAMED = "GET_FLOW_BY_ID"
        const val ANALYTICS_KEY = "d56c1f54-f6a3"
        const val PLUGIN_KEY = "1ff299cf-8db1"
    }
}
