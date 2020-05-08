package domain.flow.usecases.get.flow

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.FlowRepository
import domain.models.flow.Flow
import io.reactivex.Observable
import javax.inject.Inject

typealias GetFlowByIdUseCase = ObservableResultUseCase<String, Flow>

class GetFlowByIdUseCaseInternal @Inject constructor(
    private val flowRepository: FlowRepository
) : GetFlowByIdUseCase {

    override operator fun invoke(input: String): Observable<Result<Flow>> {
        return flowRepository.get(input)
    }

    companion object {
        const val ANALYTICS_KEY = "d56c1f54-f6a3"
        const val PLUGIN_KEY = "1ff299cf-8db1"
    }
}
